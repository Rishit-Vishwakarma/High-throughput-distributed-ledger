package com.rishit.ledger.Mapper;

import com.rishit.ledger.DTO.Response.WalletResponse;
import com.rishit.ledger.Entity.Wallet;
import org.springframework.stereotype.Component;


@Component
public class WalletMapper {
    public WalletResponse walletResponse(Wallet wallet) {
        WalletResponse walletResponse1 = new WalletResponse();

        walletResponse1.setWalletId(wallet.getWalletId());
        walletResponse1.setCurrency(wallet.getCurrency());
        walletResponse1.setStatus(wallet.getStatus());
        walletResponse1.setBalance(wallet.getBalance());
        walletResponse1.setCreatedAt(wallet.getCreatedAt());


        return walletResponse1;
    }
}
