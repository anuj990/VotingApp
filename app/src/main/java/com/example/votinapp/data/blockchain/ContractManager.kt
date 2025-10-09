package com.example.votinapp.data.blockchain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.TransactionReceipt
import java.math.BigInteger

class ContractManager {
    private val contract = Voting_sol_Voting.load(
        Web3Manager.CONTRACT_ADDRESS,
        Web3Manager.web3j,
        Web3Manager.credentials,
        Web3Manager.gasProvider
    )

    suspend fun vote(candidateIndex: Int): TransactionReceipt {
        return withContext(Dispatchers.IO) {
            contract.vote(BigInteger.valueOf(candidateIndex.toLong())).send()

        }
    }

    suspend fun getCandidates(): List<Voting_sol_Voting.Candidate> {
        return withContext(Dispatchers.IO) {
            contract.getCandidates().send()
        }
    }
}