// SPDX-License-Identifier: Unlicense
pragma solidity ^0.8.0;

contract VotingApp {
    struct Candidate {
        string name;
        uint256 votes;
    }

    Candidate[] public candidates;

    constructor() {
        candidates.push(Candidate("Anuj", 0));
        candidates.push(Candidate("Rahul", 0));
        candidates.push(Candidate("Rohit", 0));
        candidates.push(Candidate("Karan",0));
        candidates.push(Candidate("Priya", 0));
        candidates.push(Candidate("Sneha", 0));
        candidates.push(Candidate("Amit", 0));
        candidates.push(Candidate("Neha", 0));
        candidates.push(Candidate("Priya", 0));
        candidates.push(Candidate("Aman", 0));
        candidates.push(Candidate("Kiran", 0));
        candidates.push(Candidate("Vikas", 0));
    }

    function getCandidates() public view returns (Candidate[] memory){
        return candidates;
    }
    mapping(address =>bool) hasvoted;
    function vote(uint256 candidateIndex) public{
        require(!hasvoted[msg.sender],"You voted already");
        require(candidateIndex < candidates.length,"The candidate is not Valid");
        hasvoted[msg.sender] = true;
        candidates[candidateIndex].votes += 1;

    }
}
