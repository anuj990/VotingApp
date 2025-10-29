package com.example.votinapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.votinapp.data.model.Candidate
import com.example.votinapp.data.repository.VotingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class VotingViewModel(
    private val repository: VotingRepository = VotingRepository()
) : ViewModel() {

    private val _candidates = MutableStateFlow<List<Candidate>>(emptyList())

    val candidates: StateFlow<List<Candidate>> = _candidates

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadCandidates()
    }

    private fun loadCandidates() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _candidates.value = repository.getCandidates()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun vote(candidateId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.vote(candidateId)
                // Update only the voted candidate instead of reloading all candidates
                val updatedVotes = repository.getCandidateVotes(candidateId)
                _candidates.value = _candidates.value.map { candidate ->
                    if (candidate.id == candidateId) {
                        candidate.copy(votes = updatedVotes)
                    } else {
                        candidate
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}