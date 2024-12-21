package Datasource.mock

import com.wow.wowapi.Datasource.mock.MockBankDataSource
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*

internal class MockBankDataSourceTest {

    private val mockBankDataSource: MockBankDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of banks`() {
        // given


        // when
        val banks = mockBankDataSource.retrieveBanks()

        // then
        assertThat(banks).isNotEmpty
    }

    @Test
    fun `should provide some mock data`() {
        // given

        // when
        val banks = mockBankDataSource.retrieveBanks()

        // then

        assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
    }
}