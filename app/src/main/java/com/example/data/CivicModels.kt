package com.example.data

data class CandidateProfile(
    val name: String = "Samira Benali",
    val firstName: String = "Samira",
    val prefecture: String = "Préfecture de Police de Paris",
    val procedure: String = "Naturalisation par décret (Art. 21-15)",
    val streakDays: Int = 5,
    val dateDisplay: String = "18",
    val monthDisplay: String = "MARS",
    val assimilationScore: Int = 78,
    val preparationScore: Int = 84,
    val scoreDelta: Int = 6,
    val masteredSheets: Int = 28,
    val totalSheets: Int = 48,
    val masteryPercentage: Int = 68,
    val targetMastery: Int = 85,
    val languageLevel: String = "Niveau B1/B2 acquis",
    val avatarUrl: String = "https://lh3.googleusercontent.com/aida-public/AB6AXuABwjsmWKGhAv84ik3dj9EP0oRByLAB_ZX-NG6wwjhEoSMl9yWjOc--cix25HHBtSX8C9w-CPyeTZzIZ1qUdJcx-TdhD8kGNQ35LUI5piikjU2CAoROG1cGR3LudJV7usBw-UTY4nge5YuDGmuLUJ816oChsD1OC_yIsMQmeFFPxSpt_JHXer_M8Gywe5I8_w5aMb4rYXg5blf9XRmf-b3cvaNXEip-A92jcgF2WCUf3VmUmEBV_8h0pg",
    val examinerFemaleUrl: String = "https://lh3.googleusercontent.com/aida-public/AB6AXuATEodK1nMyDV_0aUwEB_qVwE3FgOv8dnp2GmEyFRwS0BmM_-MHf24iBXxsnKAggZxA60h28q72d3kpzNQwk2TfWctC5Lb-FJg1qDwJneoC_ZYkPy3fY1fvWIiIiCVgWp-6IexLfzKcvZdjU4cPt1lyvSlYBTDpAw2wClZ4uvAPZpMI6I2YPVr90no-kt-adF6eSImnlOFbo9EUVSPC1OE3X0AU3TqkjLL-xxXHcXvYeYtM2fMoVBC16Q",
    val inspectorMaleUrl: String = "https://lh3.googleusercontent.com/aida-public/AB6AXuCvRI-C5SymbGzgYMfFsyXpGvEnp3KyTjbrnAbYhf6maExUFP0v19ayfE9lUv3strwoehpnCobjhLqZaVQc9z1MkHJPq5SzoFcNpN1NRsVBU60lQe8Bqgkn5UXz5RVM1kx0pv1HJNHg2X7LBXxnL7JgBV_pwL2wgjdbmpz5zV8xB5o05wndqmMB1rQ4v4FUNug3-NpqFWfhf3YGC6RJpR51jmAJeXNmvCVN4QJIZwrnHnFkyKLxGDZUSg",
    val marianneUrl: String = "https://lh3.googleusercontent.com/aida-public/AB6AXuC84MQYUECLv5I70282814GxyJDipd6dC7vKpU0OVFE9Y-PK1Oug-PgReBemOIHOHWaqTjFJb8izN8y4oDEmfzyTcfsn4Ey0a--uqdRfF8iWWMtiJVAWJFOFWVLRRFarrObtOeN9V2fdRTjS2fSNU8M-AM5Ki04Q5VcLWcjNElGZTgiIFfzaWnpxwBmb8op1o3SfTz9yLUeYIR6EQpqHhv1HlipUk_LnNe0LbSfRWXrrusBb_nyaq4HVA",
    val documentsUrl: String = "https://lh3.googleusercontent.com/aida-public/AB6AXuAV2734t0DoSrGVnaonEe2bmyp6PtHimDCk_e_4gedU6pzxjdlZrzD1vtkScXMT1YVK0RHsFjz-QqwJ_hrJ4ozRbj6C0E4VEKjLiHsI9833WeyeIw1dh_NDTPTXYm54QJKDoU1BPGrLL8PnNFTb1HE_UYCFwvpt7I2DZ25MNMPSfbgLIJjucezQ-LPkly0dJ1rqBApF4qLjKxLkQOr0dJNlonGYsUnCQ33y1WmISKjBbtjL-a7E6STUaQ",
    val rfLogoUrl: String = "https://lh3.googleusercontent.com/aida/AEtjO1Vwewpyd3fbtVV8BoutekQxsgtiuEadC69udNzRQfiEcWTI_Rqtbt_qNhNfLd8pGCm476tmzS8SW2Naqj6DoowWIpjKBbLD73vWZ7hCNg_r_tW8S6zILTY8JBS9262E66CQEcLp-ZiuzIArdRWs1BRepSxZs3f5-OdMCovM4Jb-le3S9eIZpkPjE4XTfgYoiyyoi2Hr1eAYnPea88EIpAAXVr58ABnjj-wHMQlWy-f8Hcu8uFRju7uDCKnG"
)

data class CivicPillar(
    val id: String,
    val title: String,
    val subtitle: String,
    val meta: String,
    val tag: String? = null,
    val progressNote: String? = null,
    val gradientIndex: Int,
    val description: String,
    val keyFacts: List<String>,
    val audioTranscript: String
)

data class RevisionSheet(
    val id: String,
    val tag: String,
    val subtitle: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val isQuiz: Boolean = false
)

data class OralQuestion(
    val id: Int,
    val indexText: String,
    val questionText: String,
    val sampleAnswer: String,
    val instructorAdvice: String,
    val keywords: List<String>
)

data class RecommendedExercise(
    val id: String,
    val tag: String,
    val title: String,
    val durationText: String,
    val iconType: String,
    val summary: String
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

enum class CivicChapter(
    val chapterNumber: Int,
    val title: String,
    val shortTitle: String,
    val questionRange: String,
    val description: String
) {
    PERSONNEL(1, "Vie personnelle, familiale & pro", "Personnel", "Q1 à Q113", "Motivations, intégration, vie quotidienne, respect des règles républicaines"),
    ACTUALITE(2, "Actualité & Société", "Actualité", "Q114 à Q205", "Événements récents, laïcité, débats, institutions en action, JO"),
    REPUBLIQUE(3, "République française & Institutions", "République", "Q206 à Q392", "Valeurs, devise, pouvoirs exécutif, législatif, judiciaire, symboles"),
    HISTOIRE(4, "Histoire de France", "Histoire", "Q393 à Q553", "Révolution de 1789, les 5 Républiques, guerres mondiales, grandes lois"),
    CULTURE(5, "Culture & Patrimoine français", "Culture", "Q554 à Q616", "Monuments, littérature, cinéma, gastronomie, personnalités au Panthéon"),
    GEOGRAPHIE(6, "Géographie de la France", "Géographie", "Q617 à Q683", "Régions, départements, DROM-COM, fleuves, massifs montagneux"),
    EUROPE(7, "L'Europe & l'Union Européenne", "Europe", "Q684 à Q723", "Traités fondateurs, institutions de l'UE, espace Schengen, zone euro")
}

data class GuideQuestion(
    val id: Int,
    val numberText: String, // e.g. "Question 1", "Question 195", "Question Bonus 2"
    val chapter: CivicChapter,
    val question: String,
    val answer: String,
    val dateTip: String? = null, // Tips mnémonique pour retenir la date ou formule clé !
    val isCrucial: Boolean = false, // Frequently tested in prefecture
    val keywords: List<String> = emptyList(),
    val isMastered: Boolean = false
)
