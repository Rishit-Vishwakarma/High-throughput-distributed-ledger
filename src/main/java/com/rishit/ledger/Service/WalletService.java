package com.rishit.ledger.Service;

import com.rishit.ledger.DTO.Request.CreateWalletRequest;
import com.rishit.ledger.DTO.Response.WalletResponse;
import com.rishit.ledger.Entity.User;
import com.rishit.ledger.Entity.Wallet;
import com.rishit.ledger.Enum.WalletStatus;
import com.rishit.ledger.Mapper.WalletMapper;
import com.rishit.ledger.Repository.UserRepository;
import com.rishit.ledger.Repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public WalletService(WalletRepository walletRepository, UserRepository userRepository, WalletMapper walletMapper){
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.walletMapper = walletMapper;
    }

    public Wallet getWalletById(Long walletId){
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("No wallet found for this Id."));
    }

    public WalletResponse createWallet(CreateWalletRequest createWalletRequest){

        Long userId = createWalletRequest.getUserId();
        String currency = createWalletRequest.getCurrency();
        User user1 = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found."));
        Boolean isWalletExist = walletRepository.existsByUser_UserId(userId);
        if(isWalletExist == true){
            throw new RuntimeException("Wallet for this user already exist");
        }
        Wallet wallet1 = new Wallet();
        wallet1.setUser(user1);
        wallet1.setBalance(BigDecimal.ZERO);
        wallet1.setStatus(WalletStatus.ACTIVE);
        wallet1.setCurrency(currency);

        Wallet wallet = walletRepository.save(wallet1);

        WalletResponse walletResponse = walletMapper.walletResponse(wallet);
        return walletResponse;
    }

    public String deposit(Long walletId, BigDecimal amount){
        Wallet wallet1 = walletRepository.findById(walletId).orElseThrow(() -> new RuntimeException("Wallet for this user do not exist, Please first create a wallet."));
        Boolean isActive = wallet1.getStatus().equals(WalletStatus.ACTIVE);
        BigDecimal currentBalance;

        if(isActive == false){
            throw new RuntimeException("Wallet INACTIVE. Contect to your nearest bank for more info...");
        }

        if(amount != null && amount.compareTo(BigDecimal.ZERO) > 0){
            currentBalance = wallet1.getBalance().add(amount);
            wallet1.setBalance(currentBalance);
        }else{
            throw new RuntimeException("Invalid amount. Please enter a vaild amount.");
        }

        walletRepository.save(wallet1);
        return "Amount: " + currentBalance + ". Added to your wallet successfully...";
    }

}
