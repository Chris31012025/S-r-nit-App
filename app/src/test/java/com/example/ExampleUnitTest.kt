package com.example

import com.example.data.HistoricPeriod
import com.example.data.LivretCategory
import com.example.data.LivretDuCitoyenRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun testLivretDuCitoyen_historicDatesHaveMnemonicTips() {
    val dates = LivretDuCitoyenRepository.historicDates
    assertTrue("Should have at least 20 historic dates", dates.size >= 20)

    dates.forEach { date ->
      assertTrue("Date id ${date.id} should have valid year", date.year > 0)
      assertTrue("Date id ${date.id} title should not be blank", date.title.isNotBlank())
      assertTrue("Date id ${date.id} mnemonicTip should not be blank", date.mnemonicTip.isNotBlank())
      assertTrue("Date id ${date.id} summary should not be blank", date.summary.isNotBlank())
      assertTrue("Date id ${date.id} officialContext should not be blank", date.officialContext.isNotBlank())
    }
  }

  @Test
  fun testLivretDuCitoyen_specificRequestedMnemonics() {
    val dates = LivretDuCitoyenRepository.historicDates

    // Loi IVG 1975
    val ivg1975 = dates.find { it.year == 1975 }
    assertNotNull("Loi IVG 1975 must exist", ivg1975)
    assertTrue("Mnemonic should mention Veil and Valery", ivg1975!!.mnemonicTip.contains("VEIL", ignoreCase = true))

    // 5eme Republique 1958
    val rep1958 = dates.find { it.year == 1958 }
    assertNotNull("Ve République 1958 must exist", rep1958)
    assertTrue("Mnemonic should mention calculation formula 5-1=4 and 5*2=10", rep1958!!.mnemonicTip.contains("5 - 1 = 4"))

    // 1789 Prise de la Bastille
    val rev1789 = dates.find { it.year == 1789 }
    assertNotNull("1789 must exist", rev1789)
    assertTrue("Mnemonic should mention consecutive digits", rev1789!!.mnemonicTip.contains("7, 8, 9"))

    // 2024 IVG dans la Constitution (actualité)
    val ivg2024 = dates.find { it.year == 2024 }
    assertNotNull("2024 constitution IVG must exist", ivg2024)
  }

  @Test
  fun testLivretDuCitoyen_chaptersAndArticles() {
    val chapters = LivretDuCitoyenRepository.chapters
    assertEquals("Must have 5 official chapters", 5, chapters.size)

    chapters.forEach { ch ->
      assertTrue("Chapter ${ch.id} must have articles", ch.articles.isNotEmpty())
      ch.articles.forEach { art ->
        assertTrue("Article ${art.title} must have content", art.content.isNotBlank())
        assertTrue("Article ${art.title} must have keyPoints", art.keyPoints.isNotEmpty())
      }
    }
  }
}

