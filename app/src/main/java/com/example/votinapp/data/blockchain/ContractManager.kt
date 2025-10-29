package com.example.votinapp.data.blockchain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.web3j.protocol.core.methods.response.TransactionReceipt
import java.math.BigInteger

object ContractManager {
    private val contract by lazy {
        Voting_sol_Voting.load(
            Web3Manager.CONTRACT_ADDRESS,
            Web3Manager.web3j,
            Web3Manager.credentials,
            Web3Manager.gasProvider
        )
    }

    suspend fun vote(candidateIndex: Int): TransactionReceipt {
        return withContext(Dispatchers.IO) {
            contract.vote(candidateIndex.toBigInteger()).send()

        }
    }

    suspend fun getCandidates(): List<Voting_sol_Voting.Candidate> {
        return withContext(Dispatchers.IO) {
            contract.getCandidates().send()
        }
    }

    suspend fun getCandidateVotes(candidateIndex: Int): BigInteger {
        return withContext(Dispatchers.IO) {
            contract.candidates(candidateIndex.toBigInteger()).send().votes
        }
    }
}