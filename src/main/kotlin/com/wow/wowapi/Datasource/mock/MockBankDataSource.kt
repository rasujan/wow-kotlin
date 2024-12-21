package com.wow.wowapi.Datasource.mock

import com.wow.wowapi.Datasource.BankDataSource
import com.wow.wowapi.DTO.Bank
import org.springframework.stereotype.Repository

@Repository("mock")
class MockBankDataSource : BankDataSource {

    val banks = mutableListOf(
        Bank("1234", 1, 0.0),
        Bank("1235", 1, 0.0),
        Bank("12345", 1, 0.0)
    )

    override fun retrieveBanks(): Collection<Bank> = banks

    override fun retrieveBank(accountNumber: String): Bank =
        banks.firstOrNull() { it.accountNumber == accountNumber }
            ?: throw NoSuchElementException("No account found with account number $accountNumber")

    override fun createBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Bank with account number ${bank.accountNumber} already exists")
        }

        banks.add(bank)

        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        val current = banks.firstOrNull { it.accountNumber == bank.accountNumber }
            ?: throw NoSuchElementException("No account found with account number ${bank.accountNumber}")

        banks.remove(current)
        banks.add(current)

        return bank
    }
}
