package com.rishit.ledger.Controller;

import com.rishit.ledger.DTO.Request.CreateWalletRequest;
import com.rishit.ledger.DTO.Response.WalletResponse;
import com.rishit.ledger.Service.WalletService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {

    private final WalletService walletService;
    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping("/user/createWallet")
    public WalletResponse createWallet(@Valid @RequestBody CreateWalletRequest createWalletRequest){
        WalletResponse walletResponse = walletService.createWallet(createWalletRequest);
        return walletResponse;
    }

    @GetMapping("/user/wallet/{walletId}")
    public WalletResponse getWallet(@PathVariable Long walletId){
        return walletService.getWalletById(walletId);
    }

    @GetMapping("/user/wallet/user/{userId}")
    public WalletResponse getWalletByUserId(@PathVariable Long userId){
        return walletService.getWalletByUserId(userId);
    }
}
