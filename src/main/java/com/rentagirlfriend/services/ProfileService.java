package com.rentagirlfriend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentagirlfriend.entities.Account;
import com.rentagirlfriend.entities.Profile;
import com.rentagirlfriend.exceptions.ClientErrorException;
import com.rentagirlfriend.repositories.AccountRepository;
import com.rentagirlfriend.repositories.ProfileRepository;

@Service
public class ProfileService {
    AccountRepository accountRepository;
    ProfileRepository profileRepository;
    @Autowired
    public ProfileService(AccountRepository accountRepository, ProfileRepository profileRepository) {
        this.accountRepository = accountRepository;
        this.profileRepository = profileRepository;
    }

    public Profile registerProfileToUsername(Profile profile, String username) throws ClientErrorException {
        if (profile.getFirst_name().isBlank() || profile.getLast_name().isBlank())
            throw new ClientErrorException("names cannot be blank, these are required.");

        Optional<Account> accountOptional = Optional.ofNullable(this.accountRepository.findAccountByUsername(username));
        if (!accountOptional.isPresent())
            throw new ClientErrorException("That user does not exist.");

        Account existingAccount = accountOptional.get();
        Profile updatedProfile = this.profileRepository.save(profile);
        existingAccount.setProfile(updatedProfile);
        this.accountRepository.save(existingAccount);
        return updatedProfile;
    }
    
    public Profile findProfileByUsername(String username) throws ClientErrorException {
        if (username.isBlank())
            throw new ClientErrorException("username is empty or not detected");
        Optional<Account> accountOptional = Optional.ofNullable(this.accountRepository.findAccountByUsername(username));
        if (!accountOptional.isPresent())
            throw new ClientErrorException("user does not exist");
        Optional<Profile> profileOptional = Optional.ofNullable(accountOptional.get().getProfile());
        if (!profileOptional.isPresent())
            throw new ClientErrorException("profile of user does not exist.");

        return profileOptional.get();
    }
}
