package com.special.blockduce.transaction.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DbcEthDto {
    private Long id;
    private Long senderId;
    private Long receiverId;
    private String senderAccount;
    private String receiverAccount;
    private String blockHash;
    private Long blockNumber;
    private Long value;
    private Long transactionFee;
    private Long gasUsed;
    private String timeStamp;
    private Long isDbcEth;

    @Builder
    public DbcEthDto(Long id,Long senderId,Long receiverId,String blockHash,Long value,
                      Long transactionFee, Long gasUsed,String timeStamp,String senderAccount,
                  String receiverAccount,Long isDbcEth,Long blockNumber) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.blockHash = blockHash;
        this.value = value;
        this.transactionFee = transactionFee;
        this.gasUsed = gasUsed;
        this.timeStamp = timeStamp;
        this.isDbcEth = isDbcEth;
        this.blockNumber = blockNumber;
    }
}