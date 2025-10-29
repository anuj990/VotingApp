package com.example.votinapp.data.blockchain


import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.tx.gas.DefaultGasProvider


object Web3Manager {
    private const val NODE_URL = "https://eth-sepolia.g.alchemy.com/v2/gF1vOkDxbhxNd3JVLQbPa"
    private const val PRIVATE_KEY = "dbc741a5152529a9f17c10ddec36f96ed27f7ae92da7fb4c9cd6a8f8240ee08c"
    const val CONTRACT_ADDRESS = "0xb27A31f1b0AF2946B7F582768f03239b1eC07c2c"

    val web3j: Web3j by lazy { Web3j.build(HttpService(NODE_URL)) }
    val credentials: Credentials by lazy { Credentials.create(PRIVATE_KEY) }
    val gasProvider by lazy { DefaultGasProvider() }


}