package com.example.data

/**
 * Data structures for the official "Livret du Citoyen"
 * with integrated mnemonic tips for key historical dates.
 */

data class HistoricDateMnemonic(
    val id: Int,
    val year: Int,
    val exactDate: String, // e.g. "4 octobre 1958", "17 janvier 1975", "14 juillet 1789"
    val title: String,
    val period: HistoricPeriod,
    val summary: String,
    val mnemonicTip: String, // L'astuce mnémonique pour retenir la date
    val officialContext: String, // Importance lors de l'entretien de naturalisation
    val isCrucial: Boolean = true,
    val tags: List<String> = emptyList()
)

enum class HistoricPeriod(val displayName: String, val code: String) {
    ANCIEN_REGIME_REVOLUTION("Révolution & Fondations (1789-1804)", "REVOLUTION"),
    XIX_SIECLE("Le XIXe Siècle & Républiques (1848-1905)", "XIX"),
    GUERRES_MONDIALES("Guerres Mondiales & Libération (1914-1945)", "GUERRES"),
    VE_REPUBLIQUE("Ve République & Évolutions (1958-1999)", "VE_REP"),
    XXIE_SIECLE("Époque Contemporaine (2000-2026)", "XXIE")
}

data class LivretArticle(
    val title: String,
    val content: String,
    val keyPoints: List<String> = emptyList(),
    val officialQuote: String? = null,
    val legalReference: String? = null,
    val imageUrl: String? = null
)

data class LivretChapter(
    val id: String,
    val number: Int,
    val title: String,
    val subtitle: String,
    val category: LivretCategory,
    val articles: List<LivretArticle>,
    val relatedDateIds: List<Int> = emptyList()
)

enum class LivretCategory(val displayName: String) {
    PRINCIPES("Principes & Valeurs"),
    SYMBOLES("Symboles Nationaux"),
    DROITS_DEVOIRS("Droits & Devoirs"),
    HISTOIRE("Histoire & Repères"),
    INSTITUTIONS("Institutions & Territoires"),
    EUROPE_MONDE("Europe & Monde")
}
