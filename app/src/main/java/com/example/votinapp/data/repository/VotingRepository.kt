package com.example.votinapp.data.repository

import com.example.votinapp.data.blockchain.ContractManager
import com.example.votinapp.data.model.Candidate
import org.web3j.protocol.core.methods.response.TransactionReceipt
import java.math.BigInteger

class VotingRepository {
    private val contractManager = ContractManager
    suspend fun getCandidates():List<Candidate>{
        val contractCandidate = contractManager.getCandidates()
        return contractCandidate.mapIndexed{index,contractCandidate  ->
            Candidate(
                id = index,
                name = contractCandidate.name,
                votes = contractCandidate.votes
            )


        }
    }

    suspend fun vote(candidateId: Int): TransactionReceipt {
        return contractManager.vote(candidateId)
    }

    suspend fun getCandidateVotes(candidateId: Int): BigInteger {
        return contractManager.getCandidateVotes(candidateId)
    }
}