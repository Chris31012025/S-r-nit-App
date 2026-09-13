package com.example.data

object CivicRepository {
    val defaultProfile = CandidateProfile()

    val pillars = listOf(
        CivicPillar(
            id = "valeurs",
            title = "Principes & Valeurs",
            subtitle = "Liberté, Égalité, Fraternité, Laïcité",
            meta = "12 fiches • 15 min",
            tag = null,
            progressNote = "3 points clés validés",
            gradientIndex = 0,
            description = "Les valeurs fondamentales qui unissent la nation française et fondent le pacte républicain.",
            keyFacts = listOf(
                "La République est indivisible, laïque, démocratique et sociale (Art. 1er de la Constitution).",
                "La devise : 'Liberté, Égalité, Fraternité' gravée sur le fronton des mairies et écoles.",
                "La loi du 9 décembre 1905 garantit la liberté de conscience et sépare les Églises de l'État.",
                "L'égalité femmes-hommes est un principe inaliénable garanti par la loi."
            ),
            audioTranscript = "La République française repose sur quatre principes fondateurs : la liberté, qui permet à chacun d'agir dans le respect d'autrui ; l'égalité, qui garantit que tous les citoyens ont les mêmes droits sans distinction ; la fraternité, qui appelle à la solidarité nationale ; et la laïcité, qui garantit la neutralité de l'État et la liberté absolue de conscience."
        ),
        CivicPillar(
            id = "histoire",
            title = "Histoire de France",
            subtitle = "Grandes dates, 1789, figures clés",
            meta = "18 fiches • 25 min",
            tag = "Prioritaire",
            progressNote = "12/18 dates maîtrisées",
            gradientIndex = 1,
            description = "Les moments charnières qui ont forgé la France contemporaine, de 1789 aux traités européens.",
            keyFacts = listOf(
                "14 juillet 1789 : Prise de la Bastille et fête nationale célébrant la Fête de la Fédération de 1790.",
                "26 août 1789 : Déclaration des Droits de l'Homme et du Citoyen.",
                "1944 : Droit de vote accordé aux femmes en France par ordonnance du gouvernement provisoire.",
                "1958 : Naissance de la Cinquième République sous l'égide du Général de Gaulle."
            ),
            audioTranscript = "L'histoire de France est marquée par des tournants démocratiques majeurs. La Révolution de 1789 a aboli l'Ancien Régime et posé les bases de la souveraineté populaire. La République s'est consolidée au XIXe siècle avec l'école gratuite, laïque et obligatoire, puis au XXe siècle par la reconstruction et l'engagement européen."
        ),
        CivicPillar(
            id = "institutions",
            title = "Institutions",
            subtitle = "Président, Parlement, Lois & Justice",
            meta = "10 fiches • 12 min",
            tag = null,
            progressNote = "8/10 faits mémorisés",
            gradientIndex = 2,
            description = "Le fonctionnement démocratique, la séparation des pouvoirs et les rôles institutionnels.",
            keyFacts = listOf(
                "Le Président de la République est élu pour 5 ans au suffrage universel direct.",
                "Le Parlement se compose de l'Assemblée nationale (Palais Bourbon) et du Sénat (Palais du Luxembourg).",
                "Le Premier ministre conduit l'action du gouvernement à l'Hôtel de Matignon.",
                "Le Conseil constitutionnel veille à la conformité des lois avec la Constitution."
            ),
            audioTranscript = "La Ve République sépare le pouvoir exécutif, exercé par le Président et le Gouvernement, le pouvoir législatif détenu par le Parlement composé de l'Assemblée nationale et du Sénat, et l'autorité judiciaire indépendante qui applique les lois."
        ),
        CivicPillar(
            id = "droits",
            title = "Droits & Devoirs",
            subtitle = "Impôts, vote, respect des lois",
            meta = "8 fiches • 10 min",
            tag = "À revoir",
            progressNote = "5/8 devoirs assimilés",
            gradientIndex = 3,
            description = "Les responsabilités citoyennes, l'engagement civique et la participation à la vie de la cité.",
            keyFacts = listOf(
                "Droit de vote et d'éligibilité pour participer activement à la vie démocratique.",
                "Devoir de respecter les lois républicaines et les droits d'autrui.",
                "Contribution fiscale équitable au financement des services publics de la Nation.",
                "Devoir de défense nationale et de participation aux jurys d'assises si convoqué."
            ),
            audioTranscript = "Être citoyen français implique un équilibre fondamental entre droits et devoirs. Vous disposez de la liberté d'expression, du droit de vote et de protection, tout en ayant le devoir d'obéir aux lois, de participer à l'effort commun par l'impôt et de respecter la dignité de chacun."
        )
    )

    val revisionSheets = listOf(
        RevisionSheet(
            id = "symboles",
            tag = "ESSENTIEL",
            subtitle = "4 mémos",
            title = "Les symboles de la République",
            description = "Marianne, La Marseillaise, Drapeau tricolore, Coq",
            imageUrl = defaultProfile.marianneUrl,
            isQuiz = false
        ),
        RevisionSheet(
            id = "pieges",
            tag = "ENTRAÎNEMENT",
            subtitle = "Score actuel: 16/20",
            title = "Quiz express : 20 questions pièges",
            description = "Questions fréquentes des agents de préfecture",
            imageUrl = defaultProfile.documentsUrl,
            isQuiz = true
        )
    )

    val oralQuestions = listOf(
        OralQuestion(
            id = 1,
            indexText = "Question 01 sur 08",
            questionText = "Pour quelles raisons avez-vous choisi de vivre durablement en France ?",
            sampleAnswer = "J'ai choisi la France pour son attachement profond aux libertés fondamentales, son ouverture culturelle et les opportunités qu'elle offre. C'est ici que je construis ma vie et que je souhaite m'engager pleinement.",
            instructorAdvice = "Parlez avec sincérité de votre parcours personnel et professionnel. L'agent cherche à comprendre votre attachement réel au pays.",
            keywords = listOf("Projet de vie", "Valeurs", "Intégration", "Stabilité")
        ),
        OralQuestion(
            id = 2,
            indexText = "Question 02 sur 08",
            questionText = "Que symbolise pour vous la devise 'Liberté, Égalité, Fraternité' au quotidien ?",
            sampleAnswer = "Pour moi, la liberté me permet de penser et d'agir sans crainte ; l'égalité garantit que chacun a sa chance sans discrimination ; et la fraternité représente l'entraide et la solidarité qui nous lient tous.",
            instructorAdvice = "Donnez un exemple concret de votre quotidien où vous appliquez la fraternité ou l'entraide de quartier.",
            keywords = listOf("Solidarité", "Droits égaux", "Respect mutuel")
        ),
        OralQuestion(
            id = 3,
            indexText = "Question 03 sur 08",
            questionText = "Pourquoi souhaitez-vous devenir français(e) et quel principe de la République vous touche le plus ?",
            sampleAnswer = "Je souhaite devenir française car je partage pleinement les valeurs républicaines, notamment l'égalité des chances et la laïcité qui me permettent de m'épanouir au quotidien. C'est un engagement moral et civique envers la communauté nationale.",
            instructorAdvice = "Pensez à ancrer votre réponse dans votre parcours concret : citez un exemple précis de votre vie quotidienne ou professionnelle en France pour illustrer comment vous vivez la fraternité ou l'égalité.",
            keywords = listOf("Vie associative", "Respect mutuel", "Engagement local")
        ),
        OralQuestion(
            id = 4,
            indexText = "Question 04 sur 08",
            questionText = "Comment définissez-vous la laïcité et comment doit-elle s'appliquer dans un service public ?",
            sampleAnswer = "La laïcité garantit la liberté de croire ou de ne pas croire, tout en imposant une stricte neutralité religieuse aux agents du service public afin de traiter tous les usagers de manière parfaitement égale.",
            instructorAdvice = "Soulignez bien que la laïcité n'est pas contre les religions, mais qu'elle protège la liberté de conscience de chacun.",
            keywords = listOf("Neutralité de l'État", "Liberté de culte", "Loi de 1905")
        ),
        OralQuestion(
            id = 5,
            indexText = "Question 05 sur 08",
            questionText = "Citez un événement historique marquant de la France et expliquez son importance.",
            sampleAnswer = "La Révolution de 1789 et la prise de la Bastille le 14 juillet représentent l'affirmation des libertés et des droits de l'homme, marquant la naissance des principes démocratiques modernes.",
            instructorAdvice = "Retenez bien les grandes dates : 14 juillet 1789, 11 novembre 1918, 8 mai 1945, et 1958.",
            keywords = listOf("14 juillet 1789", "DDHC", "Fête nationale")
        ),
        OralQuestion(
            id = 6,
            indexText = "Question 06 sur 08",
            questionText = "Qui vote les lois en France et où siègent les représentants de la Nation ?",
            sampleAnswer = "Les lois sont votées par le Parlement, composé de l'Assemblée nationale au Palais Bourbon et du Sénat au Palais du Luxembourg. En cas de désaccord persistant, l'Assemblée nationale a le dernier mot.",
            instructorAdvice = "Mentionnez le bicamérisme et le fait que les députés sont élus directement par les citoyens.",
            keywords = listOf("Parlement", "Assemblée nationale", "Sénat")
        ),
        OralQuestion(
            id = 7,
            indexText = "Question 07 sur 08",
            questionText = "Quels sont les devoirs majeurs d'un citoyen français ?",
            sampleAnswer = "Respecter scrupuleusement les lois, payer ses impôts pour soutenir la collectivité, participer au vote, et respecter la dignité ainsi que les libertés de chaque concitoyen.",
            instructorAdvice = "Insistez sur la notion de solidarité nationale à travers la contribution fiscale et le civisme.",
            keywords = listOf("Respect de la loi", "Impôts", "Participation citoyenne")
        ),
        OralQuestion(
            id = 8,
            indexText = "Question 08 sur 08",
            questionText = "Quels sont les symboles officiels de la République française ?",
            sampleAnswer = "Le drapeau tricolore bleu blanc rouge, la devise 'Liberté, Égalité, Fraternité', l'hymne national 'La Marseillaise', la figure de Marianne, et le 14 juillet comme jour de fête nationale.",
            instructorAdvice = "Vous pouvez également citer le coq gaulois et le sceau de la République.",
            keywords = listOf("Drapeau tricolore", "La Marseillaise", "Marianne", "14 juillet")
        )
    )

    val recommendedExercises = listOf(
        RecommendedExercise(
            id = "laicite_ecole",
            tag = "LAÏCITÉ",
            title = "Laïcité à l'école & espace public",
            durationText = "5 mins",
            iconType = "balance",
            summary = "Comprendre l'application de la charte de la laïcité à l'école et la neutralité des fonctionnaires."
        ),
        RecommendedExercise(
            id = "cinq_republiques",
            tag = "CHRONOLOGIE",
            title = "Les 5 Républiques françaises",
            durationText = "10 mins",
            iconType = "book",
            summary = "Frise chronologique de la Première République de 1792 à la constitution de la Ve en 1958."
        ),
        RecommendedExercise(
            id = "droits_femmes",
            tag = "SOCIÉTÉ",
            title = "Droits des femmes & parité",
            durationText = "4 mins",
            iconType = "group",
            summary = "Grandes étapes de l'égalité : droit de vote en 1944, loi Veil en 1975, parité en politique."
        ),
        RecommendedExercise(
            id = "stress_oral",
            tag = "POSTURE",
            title = "Gestion du stress oral",
            durationText = "8 mins",
            iconType = "meditation",
            summary = "Exercices de respiration ventrale et posture de confiance pour dialoguer avec sérénité lors de l'entretien."
        )
    )

    val devisesQuiz = listOf(
        QuizQuestion(
            id = 1,
            question = "Que symbolisent les trois couleurs du drapeau français ?",
            options = listOf(
                "Le blanc pour la monarchie, encadré par le bleu et le rouge de Paris",
                "Le ciel, la neige et le sang des révolutionnaires",
                "La paix, la justice et la force militaire",
                "L'Europe, la France et les régions d'outre-mer"
            ),
            correctIndex = 0,
            explanation = "Le blanc était la couleur royale traditionnelle, associée aux couleurs bleu et rouge de la garde bourgeoise de Paris en 1789."
        ),
        QuizQuestion(
            id = 2,
            question = "Qui est Marianne ?",
            options = listOf(
                "La première femme présidente de l'Assemblée",
                "L'allégorie de la République et de la Liberté coiffée du bonnet phrygien",
                "Une poétesse du XIXe siècle ayant écrit La Marseillaise",
                "La reine ayant aboli les privilèges"
            ),
            correctIndex = 1,
            explanation = "Marianne incarne la République française et ses valeurs. Son buste est présent dans chaque mairie de France."
        ),
        QuizQuestion(
            id = 3,
            question = "Qui a écrit les paroles de La Marseillaise en 1792 ?",
            options = listOf(
                "Claude Joseph Rouget de Lisle",
                "Victor Hugo",
                "Jean-Jacques Rousseau",
                "Le Marquis de La Fayette"
            ),
            correctIndex = 0,
            explanation = "Rouget de Lisle a composé le Chant de guerre pour l'armée du Rhin à Strasbourg, repris ensuite par les volontaires marseillais."
        )
    )

    val piegeMairieQuiz = listOf(
        QuizQuestion(
            id = 1,
            question = "À la mairie, un usager refuse de serrer la main d'une officière d'état civil en raison de son sexe. Quelle est la règle républicaine ?",
            options = listOf(
                "La République garantit l'égalité stricte entre hommes et femmes ; un refus discriminatoire fondé sur le sexe est contraire aux valeurs républicaines et peut justifier le refus de naturalisation.",
                "L'usager a le droit d'imposer ses convictions religieuses à un agent public.",
                "L'officière doit se retirer et laisser sa place à un homme.",
                "Il n'y a aucune règle particulière concernant le respect des agents publics."
            ),
            correctIndex = 0,
            explanation = "Le Conseil d'État a confirmé qu'un comportement discriminatoire envers une représentante de l'État pour motif religieux caractérise un défaut d'assimilation."
        )
    )
}
