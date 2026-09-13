package com.example.data

object Guide707QuestionsData {

    val allQuestions: List<GuideQuestion> by lazy {
        listOf(
            // ==========================================
            // CHAPITRE 1 : QUESTIONS PERSONNELLES, FAMILIALES ET PROFESSIONNELLES
            // ==========================================
            GuideQuestion(
                id = 1,
                numberText = "Question 1",
                chapter = CivicChapter.PERSONNEL,
                question = "Pourquoi voulez-vous devenir citoyen(ne) français(e) ?",
                answer = "Devenir français est pour moi l'aboutissement naturel et sincère de mon intégration. J'ai construit ma vie, mon foyer et mon activité professionnelle en France. J'adhère profondément aux valeurs républicaines de Liberté, Égalité, Fraternité et Laïcité. Obtenir la nationalité me permettra de participer pleinement à la démocratie en exerçant mon droit de vote et en assumant l'ensemble des devoirs civiques.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Intégration", "Valeurs républicaines", "Droit de vote", "Engagement")
            ),
            GuideQuestion(
                id = 2,
                numberText = "Question 2",
                chapter = CivicChapter.PERSONNEL,
                question = "Avez-vous révisé pour cet entretien ?",
                answer = "Oui, je me suis préparé(e) avec beaucoup de sérieux en étudiant le Livret du Citoyen, l'histoire de France, le fonctionnement de nos institutions républicaines, la culture et l'actualité de notre pays.",
                dateTip = null,
                isCrucial = false,
                keywords = listOf("Préparation", "Livret du citoyen", "Institutions")
            ),
            GuideQuestion(
                id = 3,
                numberText = "Question 3",
                chapter = CivicChapter.PERSONNEL,
                question = "Qu'est-ce qu'un Français ?",
                answer = "Un Français est une personne liée à la nation par un héritage historique, culturel, civique et linguistique, et qui partage les valeurs universelles de la République : liberté, égalité, fraternité et respect de la laïcité.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Nation", "Valeurs", "Héritage", "Citoyenneté")
            ),
            GuideQuestion(
                id = 4,
                numberText = "Question 4",
                chapter = CivicChapter.PERSONNEL,
                question = "Qu'est-ce qu'un citoyen français ?",
                answer = "C'est un membre actif du corps politique national qui jouit de droits civils et politiques (droit de vote, éligibilité) et assume des devoirs (respect des lois, impôts, défense de la nation).",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Droits", "Devoirs", "Vote", "Démocratie")
            ),
            GuideQuestion(
                id = 5,
                numberText = "Question 5",
                chapter = CivicChapter.PERSONNEL,
                question = "Que changera pour vous le fait de devenir français ?",
                answer = "Cela consacrera mon appartenance pleine et entière à la communauté nationale. Sur le plan civique, j'acquerrai le droit de vote et d'éligibilité. Sur le plan symbolique, ce sera une immense fierté d'être protégé par la République et de lui être loyal.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Vote", "Reconnaissance", "Fierté", "Loyauté")
            ),
            GuideQuestion(
                id = 9,
                numberText = "Question 9",
                chapter = CivicChapter.PERSONNEL,
                question = "Avez-vous lu la Charte des droits et devoirs du citoyen français ? Que contient-elle ?",
                answer = "Oui, je l'ai lue et signée. Elle rappelle les principes de la République : l'indivisibilité, la laïcité, la démocratie, l'égalité hommes-femmes, l'obligation de respecter les lois, de s'acquitter des impôts et de participer à la défense du pays.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Charte", "Droits et devoirs", "Laïcité", "Égalité")
            ),
            GuideQuestion(
                id = 10,
                numberText = "Question 10",
                chapter = CivicChapter.PERSONNEL,
                question = "Allez-vous garder votre nationalité d'origine une fois naturalisé(e) ?",
                answer = "La loi française autorise pleinement la double nationalité. Si la loi de mon pays d'origine le permet, je conserverai ce lien familial, mais ma loyauté et mon engagement civique prioritaire iront à la France.",
                dateTip = null,
                isCrucial = false,
                keywords = listOf("Double nationalité", "Loyauté", "Législation")
            ),
            GuideQuestion(
                id = 12,
                numberText = "Question 12",
                chapter = CivicChapter.PERSONNEL,
                question = "En cas de conflit entre la France et votre pays d'origine, quel pays défendrez-vous ?",
                answer = "Sans aucune hésitation, je défendrai la France. En demandant la nationalité française, je m'engage à la fidélité absolue envers la République et au respect de mes devoirs de citoyen, y compris la défense de la nation.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Loyauté", "Défense", "Fidélité", "Patrie")
            ),
            GuideQuestion(
                id = 17,
                numberText = "Question 17",
                chapter = CivicChapter.PERSONNEL,
                question = "Quelle autorité vous octroie la nationalité une fois naturalisé(e) ?",
                answer = "La nationalité française par décret est accordée par l'État français par un décret de naturalisation signé par le Premier ministre et le ministre chargé des naturalisations (ministre de l'Intérieur), publié au Journal Officiel.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Décret", "Premier ministre", "Journal Officiel")
            ),
            GuideQuestion(
                id = 18,
                numberText = "Question 18",
                chapter = CivicChapter.PERSONNEL,
                question = "Quelle est la différence entre la nationalité et le titre de séjour de 10 ans ?",
                answer = "Le titre de séjour est une autorisation administrative de résider et de travailler en France. La nationalité confère la citoyenneté politique (droit de vote, éligibilité), un passeport français, la protection diplomatique et l'accès à tous les emplois publics de souveraineté.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Titre de séjour", "Nationalité", "Droit de vote", "Citoyenneté")
            ),
            GuideQuestion(
                id = 19,
                numberText = "Question 19",
                chapter = CivicChapter.PERSONNEL,
                question = "Qu'est-ce qu'une OQTF ?",
                answer = "Une OQTF est une 'Obligation de Quitter le Territoire Français'. C'est une mesure administrative prise par le préfet ordonnant à un étranger en situation irrégulière de quitter la France dans un délai imparti.",
                dateTip = null,
                isCrucial = false,
                keywords = listOf("OQTF", "Préfecture", "Séjour régulier")
            ),
            GuideQuestion(
                id = 27,
                numberText = "Question 27",
                chapter = CivicChapter.PERSONNEL,
                question = "Quelle est votre situation professionnelle et comment participez-vous à l'économie française ?",
                answer = "Je travaille de manière stable et déclarée en France, ce qui me permet d'être financièrement autonome, de cotiser à la Sécurité sociale et de payer mes impôts sur le revenu, participant ainsi à l'effort collectif national.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Emploi", "Cotisations", "Impôts", "Autonomie")
            ),
            GuideQuestion(
                id = 36,
                numberText = "Question 36",
                chapter = CivicChapter.PERSONNEL,
                question = "Êtes-vous membre d'une association ?",
                answer = "Oui, je m'investis (ou participe) bénévolement à des actions associatives locales (solidarité, quartier, culture ou sport). La vie associative est une magnifique opportunité de vivre la fraternité républicaine au quotidien.",
                dateTip = "💡 Tips mémorisation loi 1901 : La liberté d'association a été créée le 1er juillet 1901 (loi 1901 : 1901, numéro 1 de la liberté de s'associer au XXe siècle) !",
                isCrucial = false,
                keywords = listOf("Bénévolat", "Loi 1901", "Fraternité", "Solidarité")
            ),
            GuideQuestion(
                id = 65,
                numberText = "Question 65",
                chapter = CivicChapter.PERSONNEL,
                question = "Que pensez-vous du port du foulard ou de signes religieux en France ?",
                answer = "En France, la liberté de conscience et de culte permet à chacun de porter un signe religieux dans l'espace public dans le respect de l'ordre public. En revanche, dans les écoles publiques primaires, collèges et lycées (loi du 15 mars 2004), le port de signes religieux ostensibles est strictement interdit pour préserver la neutralité et la laïcité.",
                dateTip = "💡 Tips mémorisation 2004 : Le 15 mars 2004 (loi sur les signes ostensibles à l'école) : 2004 = 4 syllabes 'É-CO-LE-LAÏQUE' !",
                isCrucial = true,
                keywords = listOf("Laïcité", "École publique", "Loi de 2004", "Neutralité")
            ),
            GuideQuestion(
                id = 95,
                numberText = "Question 95",
                chapter = CivicChapter.PERSONNEL,
                question = "Êtes-vous d'accord avec le principe de l'école mixte ?",
                answer = "Oui, absolument. L'école mixte est un pilier de l'égalité républicaine et de l'apprentissage du respect mutuel entre filles et garçons dès le plus jeune âge.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Mixité", "Égalité", "École républicaine")
            ),
            GuideQuestion(
                id = 103,
                numberText = "Question 103",
                chapter = CivicChapter.PERSONNEL,
                question = "Quelle est votre position si votre enfant souhaite épouser une personne du même sexe ?",
                answer = "Je respecterai son choix et le soutiendrai avec amour. En France, la loi garantit le mariage pour tous depuis 2013 et l'égalité absolue de tous les couples devant la loi, sans distinction d'orientation sexuelle.",
                dateTip = "💡 Tips mémorisation Mariage pour tous : 2013 (promulgué le 17 mai 2013 par Christiane Taubira sous François Hollande). Le '13' de 2013 porte bonheur à l'égalité de tous les couples !",
                isCrucial = true,
                keywords = listOf("Mariage pour tous", "2013", "Égalité", "Tolérance")
            ),

            // ==========================================
            // CHAPITRE 2 : QUESTIONS ACTUALITÉ ET SOCIÉTÉ
            // ==========================================
            GuideQuestion(
                id = 114,
                numberText = "Question 114",
                chapter = CivicChapter.ACTUALITE,
                question = "Suivez-vous l'actualité française et par quels canaux vous tenez-vous informé(e) ?",
                answer = "Oui, je consulte quotidiennement la presse écrite et en ligne (Le Monde, Le Figaro, Franceinfo), ainsi que les journaux télévisés (France Télévisions, Arte), pour suivre la vie politique, économique et sociétale.",
                dateTip = null,
                isCrucial = false,
                keywords = listOf("Presse", "Information", "Franceinfo", "Actualité")
            ),
            GuideQuestion(
                id = 116,
                numberText = "Question 116",
                chapter = CivicChapter.ACTUALITE,
                question = "Pouvez-vous citer trois sujets d'actualité récents en France ?",
                answer = "1° Les élections municipales de 2026 renouvelant les maires pour 6 ans ; 2° Les débats budgétaires à l'Assemblée nationale avec l'usage de l'article 49.3 et les motions de censure ; 3° Les commémorations des droits fondamentaux (50 ans de la loi Veil sur l'IVG et son inscription dans la Constitution).",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Municipales", "Budget 49.3", "Loi Veil", "Actualité 2026")
            ),
            GuideQuestion(
                id = 123,
                numberText = "Question 123",
                chapter = CivicChapter.ACTUALITE,
                question = "Qu'est-ce que le blasphème et est-il puni par la loi en France ?",
                answer = "Le blasphème consiste à critiquer, parodier ou outrager des croyances ou figures religieuses. En France, le délit de blasphème n'existe pas : la loi protège les personnes contre les injures et discriminations, mais ne protège pas les croyances contre la critique.",
                dateTip = "💡 Tips mémorisation : En France, la liberté d'expression est la règle (DDHC 1789). On protège les croyants, pas les dogmes !",
                isCrucial = true,
                keywords = listOf("Blasphème", "Liberté d'expression", "Laïcité", "Loi")
            ),
            GuideQuestion(
                id = 127,
                numberText = "Question 127",
                chapter = CivicChapter.ACTUALITE,
                question = "Que pensez-vous des caricatures dans la presse française ?",
                answer = "Les caricatures font partie intégrante de la liberté de la presse et de la tradition satirique française, protégées par la loi du 29 juillet 1881. Elles incitent à la réflexion critique sans constituer une incitation à la violence.",
                dateTip = "💡 Tips mémorisation loi presse 1881 : 1881 = symétrie de chiffres (1-8-8-1) sous Jules Ferry, consécration de la liberté de presse !",
                isCrucial = true,
                keywords = listOf("Caricatures", "Liberté de la presse", "Loi de 1881")
            ),
            GuideQuestion(
                id = 135,
                numberText = "Question 135",
                chapter = CivicChapter.ACTUALITE,
                question = "Que pensez-vous du port du voile intégral (niqab ou burqa) dans l'espace public ?",
                answer = "Le voile intégral dissimulant le visage est formellement interdit dans tout l'espace public par la loi du 11 octobre 2010. Cette interdiction repose sur des exigences d'ordre public, de sécurité et sur le principe de réciprocité du vivre-ensemble.",
                dateTip = "💡 Tips mémorisation loi 2010 : 2010 = 'Zéro visage masqué' dans l'espace public !",
                isCrucial = true,
                keywords = listOf("Voile intégral", "Loi 2010", "Ordre public", "Visage découvert")
            ),
            GuideQuestion(
                id = 146,
                numberText = "Question 146",
                chapter = CivicChapter.ACTUALITE,
                question = "En quelle année et sous quel président a été adoptée la loi sur le Mariage pour tous ?",
                answer = "La loi ouvrant le mariage aux couples de même sexe a été votée le 23 avril 2013 et promulguée le 17 mai 2013 sous la présidence de François Hollande, portée par la garde des Sceaux Christiane Taubira.",
                dateTip = "💡 Tips mémorisation : 2013 : Le '13' porte bonheur à l'égalité républicaine de tous les couples !",
                isCrucial = true,
                keywords = listOf("Mariage pour tous", "2013", "Christiane Taubira", "François Hollande")
            ),
            GuideQuestion(
                id = 151,
                numberText = "Question 151",
                chapter = CivicChapter.ACTUALITE,
                question = "Que s'est-il passé le 13 novembre 2015 en France ?",
                answer = "Une série d'attentats terroristes islamistes coordonnés ont frappé Paris et Saint-Denis (le Bataclan, des terrasses de cafés et le Stade de France), faisant 130 morts et des centaines de blessés. La nation est restée unie et debout face à la terreur.",
                dateTip = "💡 Tips mémorisation : Le 13/11/2015 : 13 novembre, hommage national annuel aux 130 victimes de la liberté.",
                isCrucial = true,
                keywords = listOf("13 novembre 2015", "Bataclan", "Hommage", "Résilience")
            ),
            GuideQuestion(
                id = 160,
                numberText = "Question 160",
                chapter = CivicChapter.ACTUALITE,
                question = "Combien de médailles la France a-t-elle remportées aux Jeux Olympiques de Paris 2024 ?",
                answer = "La France a remporté un total record de 64 médailles aux JO de Paris 2024 : 16 médailles d'or, 26 médailles d'argent et 22 médailles de bronze, se classant 5e nation mondiale.",
                dateTip = "💡 Tips mémorisation JO 2024 : 64 médailles au total (16 or x 4 = 64 ! 16 d'or pour la Ville Lumière) !",
                isCrucial = true,
                keywords = listOf("Paris 2024", "64 médailles", "Léon Marchand", "Teddy Riner")
            ),
            GuideQuestion(
                id = 171,
                numberText = "Question 171",
                chapter = CivicChapter.ACTUALITE,
                question = "Quand la peine de mort a-t-elle été abolie en France et par quelle personnalité ?",
                answer = "La peine de mort a été abolie le 9 octobre 1981 sous la présidence de François Mitterrand, grâce au combat historique du ministre de la Justice Robert Badinter.",
                dateTip = "💡 Tips mémorisation : 1981 : Mitterrand élu en mai 81, Badinter abolit la peine de mort en automne (9 octobre 1981). '81 : l'échafaud au passé' !",
                isCrucial = true,
                keywords = listOf("Abolition", "1981", "Robert Badinter", "François Mitterrand")
            ),
            GuideQuestion(
                id = 174,
                numberText = "Question 174",
                chapter = CivicChapter.ACTUALITE,
                question = "Quel est le lien entre Robert Badinter et le Panthéon en 2025 ?",
                answer = "Décédé en février 2024, Robert Badinter entre au Panthéon en 2025 en hommage national à son combat pour l'abolition de la peine de mort, la dignité humaine et l'État de droit.",
                dateTip = "💡 Tips mémorisation : 2025 : Hommage au Panthéon pour Badinter, grand défenseur de la justice humaine !",
                isCrucial = true,
                keywords = listOf("Panthéon 2025", "Robert Badinter", "Justice")
            ),
            GuideQuestion(
                id = 192,
                numberText = "Question 192",
                chapter = CivicChapter.ACTUALITE,
                question = "Pourquoi la France a-t-elle organisé des élections législatives anticipées en 2024 ?",
                answer = "Le 9 juin 2024, à la suite des résultats des élections européennes, le président Emmanuel Macron a prononcé la dissolution de l'Assemblée nationale en vertu de l'article 12 de la Constitution pour redonner la parole aux électeurs.",
                dateTip = "💡 Tips mémorisation : Article 12 de la Constitution = dissolution par le Président (12 comme les 12 mois pour pouvoir dissoudre à nouveau) !",
                isCrucial = true,
                keywords = listOf("Dissolution", "Article 12", "Législatives 2024")
            ),
            GuideQuestion(
                id = 195,
                numberText = "Question 195",
                chapter = CivicChapter.ACTUALITE,
                question = "Qu'est-ce que la loi Veil et pourquoi est-elle liée à l'IVG ?",
                answer = "Promulguée le 17 janvier 1975, la loi Veil dépénalise l'interruption volontaire de grossesse (IVG) en France. Portée avec courage par Simone Veil, ministre de la Santé, elle a mis fin aux avortements clandestins dangereux.",
                dateTip = "💡 Tips mémorisation : Le 'V' de 1975 pour Simone VEIL et le président VALÉRY Giscard d'Estaing (2 V = Victoire des femmes en 1975) !",
                isCrucial = true,
                keywords = listOf("Loi Veil", "1975", "Simone Veil", "IVG")
            ),
            GuideQuestion(
                id = 196,
                numberText = "Question 196",
                chapter = CivicChapter.ACTUALITE,
                question = "Pourquoi l'IVG a-t-elle été au cœur de l'actualité en 2024 et 2025 ?",
                answer = "Le 8 mars 2024, la France est devenue le premier pays au monde à inscrire la liberté garantie de recourir à l'IVG dans sa Constitution (article 34). En janvier 2025, la République a célébré les 50 ans de la loi Veil de 1975.",
                dateTip = "💡 Tips mémorisation : 8 mars 2024 (Journée internationale des droits des femmes) scellé dans la Constitution, et 1975 + 50 ans = janvier 2025 !",
                isCrucial = true,
                keywords = listOf("Constitution 2024", "50 ans loi Veil 2025", "Article 34")
            ),

            // ==========================================
            // CHAPITRE 3 : RÉPUBLIQUE FRANÇAISE ET INSTITUTIONS
            // ==========================================
            GuideQuestion(
                id = 206,
                numberText = "Question 206",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Quelles sont les valeurs de la République et la devise de la France ?",
                answer = "La devise officielle est 'Liberté, Égalité, Fraternité'. Ces trois valeurs fondamentales sont complétées par les principes d'indivisibilité, de laïcité et de solidarité démocratique.",
                dateTip = "💡 Tips mémorisation : Apparue à la Révolution de 1789, la devise est officiellement adoptée en 1848 sous la Deuxième République !",
                isCrucial = true,
                keywords = listOf("Liberté", "Égalité", "Fraternité", "Devise")
            ),
            GuideQuestion(
                id = 218,
                numberText = "Question 218",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Quand la France est-elle devenue laïque et que dit la loi de 1905 ?",
                answer = "La France a consacré la laïcité par la loi du 9 décembre 1905 relative à la séparation des Églises et de l'État : la République assure la liberté de conscience, garantit le libre exercice des cultes, mais ne reconnaît, ne salarie ni ne subventionne aucun culte.",
                dateTip = "💡 Tips mémorisation : 9 décembre 1905 (09/12/1905) : '05' pour les 5 lettres de L-A-Ï-Q-U-E, fin d'année pour la neutralité de l'État !",
                isCrucial = true,
                keywords = listOf("9 décembre 1905", "Laïcité", "Séparation Églises-État", "Liberté de culte")
            ),
            GuideQuestion(
                id = 226,
                numberText = "Question 226",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Quels sont les 4 principes constitutionnels de la République française ?",
                answer = "Selon l'article 1er de la Constitution, la France est une République 'indivisible, laïque, démocratique et sociale'. Elle assure l'égalité devant la loi de tous les citoyens sans distinction.",
                dateTip = "💡 Tips mémorisation : Retenez le mot 'ILDS' : Indivisible, Laïque, Démocratique, Sociale !",
                isCrucial = true,
                keywords = listOf("Article 1", "Indivisible", "Laïque", "Démocratique", "Sociale")
            ),
            GuideQuestion(
                id = 228,
                numberText = "Question 228",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Quels sont les grands symboles de la République française ?",
                answer = "Le drapeau tricolore (bleu, blanc, rouge), l'hymne national (La Marseillaise), Marianne avec le bonnet phrygien, la devise 'Liberté, Égalité, Fraternité', le 14 Juillet (fête nationale), le coq gaulois, le faisceau de licteur et le Grand Sceau de France.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Drapeau", "Marseillaise", "Marianne", "14 Juillet", "Coq")
            ),
            GuideQuestion(
                id = 244,
                numberText = "Question 244",
                chapter = CivicChapter.REPUBLIQUE,
                question = "En quelle année et quel jour la Cinquième République a-t-elle été instaurée ?",
                answer = "La Cinquième République a été fondée le 4 octobre 1958 avec la promulgation de la Constitution rédigée sous l'autorité du général Charles de Gaulle et de Michel Debré.",
                dateTip = "💡 Tips mémorisation : Le chiffre 5 est en pilier : 5 - 1 = 4 (le jour : 4) / (5 * 2) = 10 (le mois : octobre) / 1958 ! (04/10/1958)",
                isCrucial = true,
                keywords = listOf("4 octobre 1958", "Ve République", "De Gaulle", "Constitution")
            ),
            GuideQuestion(
                id = 248,
                numberText = "Question 248",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Qui est le président de la République actuel et quel est son mandat ?",
                answer = "Le président de la République actuel est Emmanuel Macron (élu en 2017 et réélu en 2022). Son mandat est de 5 ans (quinquennat), renouvelable une seule fois consécutivement.",
                dateTip = "💡 Tips mémorisation quinquennat : Modifié en 2000 sous Jacques Chirac, passant de 7 ans à 5 ans dès 2002 !",
                isCrucial = true,
                keywords = listOf("Emmanuel Macron", "5 ans", "Quinquennat", "Élysée")
            ),
            GuideQuestion(
                id = 256,
                numberText = "Question 256",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Citez dans l'ordre les 8 présidents de la Cinquième République.",
                answer = "1. Charles de Gaulle (1959-1969), 2. Georges Pompidou (1969-1974), 3. Valéry Giscard d'Estaing (1974-1981), 4. François Mitterrand (1981-1995), 5. Jacques Chirac (1995-2007), 6. Nicolas Sarkozy (2007-2012), 7. François Hollande (2012-2017), 8. Emmanuel Macron (depuis 2017).",
                dateTip = "💡 Tips mémorisation : 'De Pompidou à Giscard, Mitterrand et Chirac ouvrent la voie à Sarkozy, Hollande et Macron' !",
                isCrucial = true,
                keywords = listOf("8 Présidents", "De Gaulle", "Mitterrand", "Chirac", "Macron")
            ),
            GuideQuestion(
                id = 263,
                numberText = "Question 263",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Quels sont les 3 types de collectivités territoriales de droit commun en France ?",
                answer = "Les 3 collectivités territoriales sont : les communes (~35 000 mairies), les départements (101 départements) et les régions (18 régions, dont 13 métropolitaines et 5 d'outre-mer).",
                dateTip = "💡 Tips mémorisation : Du local au régional : Commune (maire) -> Département (président de conseil dép.) -> Région (président de conseil rég.) !",
                isCrucial = true,
                keywords = listOf("Communes", "Départements", "Régions", "Collectivités")
            ),
            GuideQuestion(
                id = 274,
                numberText = "Question 274",
                chapter = CivicChapter.REPUBLIQUE,
                question = "En quelle année les femmes ont-elles obtenu le droit de vote en France ?",
                answer = "Le droit de vote et d'éligibilité des femmes a été accordé par ordonnance du général de Gaulle le 21 avril 1944. Elles ont voté pour la première fois aux élections municipales du 29 avril 1945.",
                dateTip = "💡 Tips mémorisation : 1944 : Deux '4' côte à côte pour l'égalité parfaite homme-femme devant l'urne !",
                isCrucial = true,
                keywords = listOf("21 avril 1944", "Vote des femmes", "Éligibilité", "GPRF")
            ),
            GuideQuestion(
                id = 275,
                numberText = "Question 275",
                chapter = CivicChapter.REPUBLIQUE,
                question = "En quelle année le suffrage universel masculin a-t-il été instauré ?",
                answer = "Le suffrage universel masculin a été instauré en 1848, sous la Deuxième République, marquant la fin du suffrage censitaire.",
                dateTip = "💡 Tips mémorisation : 1848 : L'année où naît la Deuxième République, le droit de vote s'ouvre à tous les citoyens masculins (4x2=8) !",
                isCrucial = true,
                keywords = listOf("1848", "Suffrage universel masculin", "Deuxième République")
            ),
            GuideQuestion(
                id = 284,
                numberText = "Question 284",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Quels sont les trois pouvoirs constitutionnels et leurs sièges ?",
                answer = "1° Pouvoir exécutif : Président (Palais de l'Élysée) et Premier ministre (Hôtel de Matignon) ; 2° Pouvoir législatif : Parlement = Assemblée nationale (Palais Bourbon) + Sénat (Palais du Luxembourg) ; 3° Pouvoir judiciaire indépendant : Cours et tribunaux (Cour de cassation au Palais de Justice de Paris, Conseil d'État au Palais-Royal).",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Élysée", "Matignon", "Palais Bourbon", "Palais du Luxembourg")
            ),
            GuideQuestion(
                id = 287,
                numberText = "Question 287",
                chapter = CivicChapter.REPUBLIQUE,
                question = "De quoi est composé le Parlement français et combien compte-t-il de parlementaires ?",
                answer = "Le Parlement est bicaméral : il comprend l'Assemblée nationale avec 577 députés (élus au suffrage universel direct pour 5 ans) et le Sénat avec 348 sénateurs (élus au suffrage indirect pour 6 ans). Total : 925 parlementaires.",
                dateTip = "💡 Tips mémorisation : 577 députés (chambre basse directe) + 348 sénateurs (chambre haute indirecte). En cas de désaccord, l'Assemblée nationale a le dernier mot !",
                isCrucial = true,
                keywords = listOf("577 députés", "348 sénateurs", "Bicamérisme", "Dernier mot")
            ),
            GuideQuestion(
                id = 306,
                numberText = "Question 306",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Qui est le président du Sénat et quel est son rôle clé en cas de vacance présidentielle ?",
                answer = "Le président du Sénat est Gérard Larcher. Il est le deuxième personnage de l'État : en cas de décès, démission ou empêchement du président de la République, il assure l'intérim de la présidence (comme Alain Poher en 1969 et 1974).",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Gérard Larcher", "Président du Sénat", "Intérim présidentiel")
            ),
            GuideQuestion(
                id = 313,
                numberText = "Question 313",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Qui préside l'Assemblée nationale ?",
                answer = "La présidente de l'Assemblée nationale est Yaël Braun-Pivet (première femme élue à cette fonction en 2022 et réélue en juillet 2024). Elle est le quatrième personnage de l'État.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Yaël Braun-Pivet", "Assemblée nationale", "Palais Bourbon")
            ),
            GuideQuestion(
                id = 327,
                numberText = "Question 327",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Qu'est-ce que l'article 49 alinéa 3 (le '49.3') de la Constitution ?",
                answer = "Le 49.3 permet au Premier ministre d'engager la responsabilité du gouvernement devant l'Assemblée nationale pour faire adopter un texte sans vote, sauf si une motion de censure est déposée sous 24h et votée à la majorité absolue (289 voix). Si la censure est votée, le gouvernement démissionne.",
                dateTip = "💡 Tips mémorisation : 49.3 = 24 heures pour déposer la censure, 289 voix (577/2 + 1) requises pour faire tomber le gouvernement !",
                isCrucial = true,
                keywords = listOf("49.3", "Motion de censure", "Majorité absolue 289")
            ),
            GuideQuestion(
                id = 360,
                numberText = "Question 360",
                chapter = CivicChapter.REPUBLIQUE,
                question = "Quel est le rôle du Conseil constitutionnel et qui le compose ?",
                answer = "Le Conseil constitutionnel (Palais-Royal) veille à la régularité des élections et à la conformité des lois avec la Constitution. Il comprend 9 membres nommés pour 9 ans non renouvelables (3 par le Président, 3 par le président de l'AN, 3 par le président du Sénat).",
                dateTip = "💡 Tips mémorisation : 9 membres, nommés pour 9 ans : la règle des deux 9 pour les 'Sages' !",
                isCrucial = true,
                keywords = listOf("Conseil constitutionnel", "9 membres", "9 ans", "Sages")
            ),

            // ==========================================
            // CHAPITRE 4 : HISTOIRE DE FRANCE ET GRANDES DATES
            // ==========================================
            GuideQuestion(
                id = 393,
                numberText = "Question 393",
                chapter = CivicChapter.HISTOIRE,
                question = "Quelles sont les causes de la Révolution française de 1789 ?",
                answer = "Une crise financière majeure, l'injustice de la société d'ordres (le Tiers-État supportait les impôts tandis que noblesse et clergé étaient exemptés), les mauvaises récoltes, et les idées des Lumières prônant l'égalité et la souveraineté du peuple.",
                dateTip = "💡 Tips mémorisation 1789 : Les chiffres se suivent : 1 - 7 - 8 - 9 ! C'est le compte à rebours de la liberté !",
                isCrucial = true,
                keywords = listOf("1789", "Tiers-État", "Lumières", "Crise financière")
            ),
            GuideQuestion(
                id = 403,
                numberText = "Question 403",
                chapter = CivicChapter.HISTOIRE,
                question = "Pourquoi célèbre-t-on le 14 juillet en France ?",
                answer = "Le 14 juillet commémore la prise de la Bastille le 14 juillet 1789 (symbole de la fin de l'arbitraire royal) et la Fête de la Fédération du 14 juillet 1790 (union de la Nation autour de la loi). C'est la Fête nationale officielle depuis 1880.",
                dateTip = "💡 Tips mémorisation : 14 = 7 x 2 (double 7 en juillet, 7e mois). Et 1789 : 1-7-8-9 !",
                isCrucial = true,
                keywords = listOf("14 juillet", "Bastille", "Fédération", "Fête nationale")
            ),
            GuideQuestion(
                id = 410,
                numberText = "Question 410",
                chapter = CivicChapter.HISTOIRE,
                question = "Quand a été proclamée la Première République française ?",
                answer = "La Première République a été proclamée le 21 septembre 1792 par la Convention nationale au lendemain de la victoire de Valmy, abolissant la royauté de Louis XVI.",
                dateTip = "💡 Tips mémorisation 1792 : 21 septembre (jour de l'équinoxe d'automne) = l'An I de la République !",
                isCrucial = true,
                keywords = listOf("21 septembre 1792", "Première République", "Convention", "Valmy")
            ),
            GuideQuestion(
                id = 412,
                numberText = "Question 412",
                chapter = CivicChapter.HISTOIRE,
                question = "Quand le roi Louis XVI a-t-il été exécuté ?",
                answer = "Louis XVI a été guillotiné sur la place de la Révolution (actuelle place de la Concorde) à Paris le 21 janvier 1793 après son procès pour trahison.",
                dateTip = "💡 Tips mémorisation 1793 : Le 21 janvier 1793 : le 21 comme le 21 septembre 1792 !",
                isCrucial = true,
                keywords = listOf("21 janvier 1793", "Louis XVI", "Guillotine", "Concorde")
            ),
            GuideQuestion(
                id = 422,
                numberText = "Question 422",
                chapter = CivicChapter.HISTOIRE,
                question = "Quand Napoléon Bonaparte est-il devenu empereur des Français ?",
                answer = "Napoléon Bonaparte a été sacré empereur sous le nom de Napoléon Ier le 2 décembre 1804 à Notre-Dame de Paris par le pape Pie VII, fondant le Premier Empire.",
                dateTip = "💡 Tips mémorisation : 2 décembre 1804 : Napoléon sacré le 2/12/1804, et remportera Austerlitz exactement un an plus tard le 2/12/1805 !",
                isCrucial = true,
                keywords = listOf("2 décembre 1804", "Napoléon Ier", "Sacre", "Premier Empire")
            ),
            GuideQuestion(
                id = 423,
                numberText = "Question 423",
                chapter = CivicChapter.HISTOIRE,
                question = "Quand et comment la Deuxième République a-t-elle été proclamée ?",
                answer = "Elle a été proclamée le 24 février 1848 après l'abdication du roi Louis-Philippe lors de la révolution de 1848. Elle a instauré le suffrage universel masculin et aboli l'esclavage.",
                dateTip = "💡 Tips mémorisation 1848 : 4 x 2 = 8 ! Février (2e mois) pour la 2e République (24/02/1848) !",
                isCrucial = true,
                keywords = listOf("24 février 1848", "Deuxième République", "Suffrage masculin", "Schœlcher")
            ),
            GuideQuestion(
                id = 474,
                numberText = "Question 474",
                chapter = CivicChapter.HISTOIRE,
                question = "Quand l'esclavage a-t-il été définitivement aboli en France et par qui ?",
                answer = "L'esclavage a été définitivement aboli le 27 avril 1848 sous la Deuxième République, grâce au décret rédigé par Victor Schœlcher.",
                dateTip = "💡 Tips mémorisation : 27 avril 1848 : 2 mois après février 48, Schœlcher libère au printemps 1848 (4x2=8) !",
                isCrucial = true,
                keywords = listOf("27 avril 1848", "Abolition esclavage", "Victor Schœlcher")
            ),
            GuideQuestion(
                id = 428,
                numberText = "Question 428",
                chapter = CivicChapter.HISTOIRE,
                question = "Quand la Troisième République a-t-elle été proclamée ?",
                answer = "La Troisième République a été proclamée le 4 septembre 1870 par Léon Gambetta à l'Hôtel de Ville de Paris, après la défaite de Napoléon III à Sedan contre la Prusse.",
                dateTip = "💡 Tips mémorisation : 4 septembre 1870 : 1870 = Sedan. Le 4/9/70 ouvre la plus longue République moderne (70 ans, jusqu'en 1940) !",
                isCrucial = true,
                keywords = listOf("4 septembre 1870", "Troisième République", "Gambetta", "Sedan")
            ),
            GuideQuestion(
                id = 439,
                numberText = "Question 439",
                chapter = CivicChapter.HISTOIRE,
                question = "Quelles sont les dates de la Première et de la Seconde Guerre mondiale ?",
                answer = "Première Guerre mondiale : 28 juillet 1914 au 11 novembre 1918 (armistice à Rethondes). Seconde Guerre mondiale : 1er septembre 1939 (invasion de la Pologne) au 8 mai 1945 en Europe (capitulation nazie) et 2 septembre 1945 dans le monde.",
                dateTip = "💡 Tips mémorisation : 11/11 à 11h (1918) pour la 1ère guerre ! Et 8 mai 1945 (8-5=3 grands alliés) pour la 2nde !",
                isCrucial = true,
                keywords = listOf("1914-1918", "1939-1945", "11 novembre", "8 mai")
            ),
            GuideQuestion(
                id = 455,
                numberText = "Question 455",
                chapter = CivicChapter.HISTOIRE,
                question = "Que s'est-il passé le 18 juin 1940 ?",
                answer = "Le général de Gaulle a lancé depuis Londres son célèbre Appel du 18 Juin sur les ondes de la BBC, appelant les Français à refuser la capitulation et à poursuivre le combat dans la Résistance.",
                dateTip = "💡 Tips mémorisation : 18 juin 1940 : 18 = 6 x 3 (juin est le 6e mois). De Gaulle allume la flamme de la France libre !",
                isCrucial = true,
                keywords = listOf("18 juin 1940", "De Gaulle", "BBC", "Résistance")
            ),
            GuideQuestion(
                id = 456,
                numberText = "Question 456",
                chapter = CivicChapter.HISTOIRE,
                question = "Qui était Jean Moulin et pourquoi repose-t-il au Panthéon ?",
                answer = "Héros de la Résistance, Jean Moulin a été chargé par de Gaulle d'unifier les mouvements clandestins et a créé le Conseil National de la Résistance (CNR) en 1943. Arrêté et torturé par la Gestapo sans parler, il est entré au Panthéon en 1964.",
                dateTip = "💡 Tips mémorisation : 21 juin 1943 : arrêté le premier jour de l'été à Caluire par Klaus Barbie. 1964 : discours mémorable d'André Malraux !",
                isCrucial = true,
                keywords = listOf("Jean Moulin", "CNR 1943", "Panthéon 1964", "Résistance")
            ),
            GuideQuestion(
                id = 434,
                numberText = "Question 434",
                chapter = CivicChapter.HISTOIRE,
                question = "Quelles sont les 5 plages du débarquement du 6 juin 1944 en Normandie ?",
                answer = "Les 5 plages sont : Utah Beach et Omaha Beach (secteur américain), Gold Beach et Sword Beach (secteur britannique), et Juno Beach (secteur canadien).",
                dateTip = "💡 Tips mémorisation : Le 6 du 6 à 6h du matin ! (6 juin 1944). Mnémonique des 5 plages : 'Un Ours Goûte Joyeusement le Saumon' (Utah, Omaha, Gold, Juno, Sword) !",
                isCrucial = true,
                keywords = listOf("6 juin 1944", "D-Day", "Utah", "Omaha", "Gold", "Juno", "Sword")
            ),
            GuideQuestion(
                id = 533,
                numberText = "Question 533",
                chapter = CivicChapter.HISTOIRE,
                question = "À quelles dates ont été établies les cinq Républiques françaises ?",
                answer = "1ère République : 21 septembre 1792 ; 2ème République : 24 février 1848 ; 3ème République : 4 septembre 1870 ; 4ème République : 27 octobre 1946 ; 5ème République : 4 octobre 1958.",
                dateTip = "💡 Tips mémorisation Ve République : 5 - 1 = 4 (le 4) / 5 x 2 = 10 (octobre) / 1958 !",
                isCrucial = true,
                keywords = listOf("1792", "1848", "1870", "1946", "1958")
            ),

            // ==========================================
            // CHAPITRE 5 : CULTURE, ARTS ET PATRIMOINE FRANÇAIS
            // ==========================================
            GuideQuestion(
                id = 554,
                numberText = "Question 554",
                chapter = CivicChapter.CULTURE,
                question = "Présentez un monument historique emblématique de la France.",
                answer = "La Tour Eiffel, conçue par Gustave Eiffel pour l'Exposition universelle de 1889 (centenaire de la Révolution). Haute de 330 mètres, elle symbolise le génie technologique et le rayonnement culturel français dans le monde entier.",
                dateTip = "💡 Tips mémorisation 1889 : 1789 + 100 ans = 1889 ! La Tour Eiffel a été bâtie pour célébrer le centenaire de la Révolution !",
                isCrucial = true,
                keywords = listOf("Tour Eiffel", "Gustave Eiffel", "1889", "Exposition universelle")
            ),
            GuideQuestion(
                id = 559,
                numberText = "Question 559",
                chapter = CivicChapter.CULTURE,
                question = "Qui était Molière et quelles sont ses pièces célèbres ?",
                answer = "Jean-Baptiste Poquelin, dit Molière (1622-1673), est le plus grand dramaturge français du XVIIe siècle. Il a écrit Le Malade imaginaire, Tartuffe, L'Avare et Le Bourgeois gentilhomme, donnant au français le surnom de 'langue de Molière'.",
                dateTip = "💡 Tips mémorisation : XVIIe siècle, le siècle de Louis XIV le Roi-Soleil qui protégeait la troupe de Molière !",
                isCrucial = true,
                keywords = listOf("Molière", "Langue de Molière", "Tartuffe", "L'Avare")
            ),
            GuideQuestion(
                id = 568,
                numberText = "Question 568",
                chapter = CivicChapter.CULTURE,
                question = "Qui est l'auteur de Madame Bovary et de Germinal ?",
                answer = "Madame Bovary a été écrit par Gustave Flaubert (1857). Germinal a été écrit par Émile Zola (1885), fresque naturaliste décrivant la grève et la vie des mineurs du Nord.",
                dateTip = null,
                isCrucial = false,
                keywords = listOf("Flaubert", "Zola", "Littérature française")
            ),
            GuideQuestion(
                id = 569,
                numberText = "Question 569",
                chapter = CivicChapter.CULTURE,
                question = "Citez des géants de la littérature française et leurs chefs-d'œuvre.",
                answer = "Victor Hugo (Les Misérables, Notre-Dame de Paris), Albert Camus (L'Étranger, La Peste, Prix Nobel 1957), Marcel Proust (À la recherche du temps perdu) et Voltaire (Candide).",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Victor Hugo", "Albert Camus", "Voltaire", "Les Misérables")
            ),
            GuideQuestion(
                id = 589,
                numberText = "Question 589",
                chapter = CivicChapter.CULTURE,
                question = "Citez deux grands scientifiques français qui ont révolutionné le monde.",
                answer = "1° Louis Pasteur : inventeur du vaccin contre la rage (1885) et pionnier de la microbiologie ; 2° Marie Curie : découvreuse du radium et polonium, seule femme double prix Nobel (Physique 1903, Chimie 1911), reposant au Panthéon.",
                dateTip = "💡 Tips mémorisation : 1885 = Pasteur sauve le jeune Joseph Meister de la rage ! Marie Curie = deux Nobel dans 2 sciences distinctes.",
                isCrucial = true,
                keywords = listOf("Louis Pasteur", "Marie Curie", "Vaccin", "Prix Nobel")
            ),
            GuideQuestion(
                id = 603,
                numberText = "Question 603",
                chapter = CivicChapter.CULTURE,
                question = "Quelle fête populaire célèbre la musique en France et à quelle date ?",
                answer = "La Fête de la Musique, créée en 1982 par Jack Lang, a lieu chaque 21 juin, jour du solstice d'été. C'est une grande fête citoyenne et gratuite où tous les musiciens jouent dans les rues.",
                dateTip = "💡 Tips mémorisation : 21 juin = premier jour de l'été, la nuit la plus courte de l'année pour faire de la musique partout !",
                isCrucial = false,
                keywords = listOf("21 juin", "Fête de la Musique", "Jack Lang", "Solstice")
            ),
            GuideQuestion(
                id = 718,
                numberText = "Question 718",
                chapter = CivicChapter.CULTURE,
                question = "Quelle est la devise gravée sur le Panthéon et citez 4 personnalités qui y reposent.",
                answer = "Devise : 'Aux grands hommes la patrie reconnaissante'. Y reposent notamment : Voltaire, Jean-Jacques Rousseau, Victor Hugo, Émile Zola, Jean Jaurès, Jean Moulin, Marie Curie, Simone Veil et Robert Badinter.",
                dateTip = "💡 Tips mémorisation : Simone Veil entrée en 2018, Robert Badinter en 2025. Deux immenses figures de la justice et de l'égalité !",
                isCrucial = true,
                keywords = listOf("Panthéon", "Aux grands hommes", "Simone Veil", "Victor Hugo")
            ),

            // ==========================================
            // CHAPITRE 6 : GÉOGRAPHIE DE LA FRANCE
            // ==========================================
            GuideQuestion(
                id = 617,
                numberText = "Question 617",
                chapter = CivicChapter.GEOGRAPHIE,
                question = "Quels sont les pays limitrophes de la France métropolitaine ?",
                answer = "La France métropolitaine partage ses frontières terrestres avec 8 pays : la Belgique, le Luxembourg, l'Allemagne, la Suisse, l'Italie, Monaco, l'Espagne et l'Andorre. En outre-mer, la Guyane a une frontière terrestre avec le Brésil et le Suriname.",
                dateTip = "💡 Tips mémorisation frontière : La plus longue frontière terrestre de la France n'est pas en Europe : c'est celle avec le Brésil en Guyane (730 km) !",
                isCrucial = true,
                keywords = listOf("8 pays", "Belgique", "Allemagne", "Espagne", "Brésil")
            ),
            GuideQuestion(
                id = 624,
                numberText = "Question 624",
                chapter = CivicChapter.GEOGRAPHIE,
                question = "Quels sont les 5 départements et régions d'outre-mer (DROM) ?",
                answer = "Les 5 DROM sont : la Guadeloupe (971), la Martinique (972), la Guyane (973), La Réunion (974) et Mayotte (976). Ils ont le même statut que les départements métropolitains.",
                dateTip = "💡 Tips mémorisation : 971 à 976 : 'G-M-G-R-M' : Guadeloupe, Martinique, Guyane, Réunion, Mayotte !",
                isCrucial = true,
                keywords = listOf("DROM", "Guadeloupe", "Martinique", "Guyane", "Réunion", "Mayotte")
            ),
            GuideQuestion(
                id = 649,
                numberText = "Question 649",
                chapter = CivicChapter.GEOGRAPHIE,
                question = "Quels sont les 5 grands fleuves français et quel est le plus long ?",
                answer = "Les 5 fleuves sont la Loire (1 012 km, le plus long fleuve coulant entièrement en France), la Seine (traverse Paris vers la Manche), le Rhône (naît en Suisse et se jette dans la Méditerranée), la Garonne (vers l'estuaire de la Gironde) et le Rhin (fleuve frontière avec l'Allemagne).",
                dateTip = "💡 Tips mémorisation : 'La Seine, la Loire, le Rhône, la Garonne et le Rhin' : La Loire est la reine des fleuves avec plus de 1 000 km !",
                isCrucial = true,
                keywords = listOf("Loire 1012 km", "Seine", "Rhône", "Garonne", "Rhin")
            ),
            GuideQuestion(
                id = 665,
                numberText = "Question 665",
                chapter = CivicChapter.GEOGRAPHIE,
                question = "Quel est le point culminant de la France et où se situe-t-il ?",
                answer = "Le Mont Blanc, culminant à 4 807 mètres d'altitude dans le massif des Alpes (Haute-Savoie), est le plus haut sommet de France et d'Europe occidentale.",
                dateTip = "💡 Tips mémorisation : 4 807 m : '4 x 2 = 8, 0, 7' : presque 5 kilomètres de hauteur dans le ciel des Alpes !",
                isCrucial = true,
                keywords = listOf("Mont Blanc", "4807 mètres", "Alpes", "Haute-Savoie")
            ),
            GuideQuestion(
                id = 682,
                numberText = "Question 682",
                chapter = CivicChapter.GEOGRAPHIE,
                question = "Quelles sont les grandes chaînes de montagnes en France ?",
                answer = "Les Alpes (frontière Italie/Suisse), les Pyrénées (frontière Espagne), le Massif Central (volcans d'Auvergne), le Jura (frontière Suisse) et les Vosges.",
                dateTip = null,
                isCrucial = true,
                keywords = listOf("Alpes", "Pyrénées", "Massif Central", "Jura", "Vosges")
            ),

            // ==========================================
            // CHAPITRE 7 : QUESTIONS SUR L'EUROPE ET L'UNION EUROPÉENNE
            // ==========================================
            GuideQuestion(
                id = 684,
                numberText = "Question 684",
                chapter = CivicChapter.EUROPE,
                question = "Pourquoi l'Union européenne a-t-elle été créée après 1945 ?",
                answer = "L'UE a été créée pour sceller une paix durable entre les nations européennes après les ravages de la Seconde Guerre mondiale, favoriser la coopération économique et préserver la démocratie et les droits de l'homme.",
                dateTip = "💡 Tips mémorisation 9 mai 1950 : Déclaration Robert Schuman = Journée de l'Europe le 9 mai !",
                isCrucial = true,
                keywords = listOf("Paix", "Coopération", "Robert Schuman", "9 mai")
            ),
            GuideQuestion(
                id = 685,
                numberText = "Question 685",
                chapter = CivicChapter.EUROPE,
                question = "Quel traité fondateur a créé la CEE et en quelle année ?",
                answer = "Le Traité de Rome, signé le 25 mars 1957, a créé la Communauté Économique Européenne (CEE) avec six États fondateurs : la France, l'Allemagne de l'Ouest, l'Italie, la Belgique, les Pays-Bas et le Luxembourg.",
                dateTip = "💡 Tips mémorisation : 25 mars 1957 : 6 pays fondateurs. 5 + 7 = 12 étoiles d'or sur le drapeau !",
                isCrucial = true,
                keywords = listOf("Traité de Rome", "25 mars 1957", "6 pays fondateurs", "CEE")
            ),
            GuideQuestion(
                id = 692,
                numberText = "Question 692",
                chapter = CivicChapter.EUROPE,
                question = "Combien de pays compte l'Union européenne et depuis quand ?",
                answer = "L'Union européenne compte 27 États membres depuis le 31 janvier 2020, date de la sortie effective du Royaume-Uni (Brexit). Le dernier pays à avoir adhéré est la Croatie le 1er juillet 2013.",
                dateTip = "💡 Tips mémorisation : 27 pays aujourd'hui (28 - 1 = 27 au Brexit en 2020) !",
                isCrucial = true,
                keywords = listOf("27 États membres", "Brexit 2020", "Croatie 2013")
            ),
            GuideQuestion(
                id = 693,
                numberText = "Question 693",
                chapter = CivicChapter.EUROPE,
                question = "Quels sont les symboles officiels de l'Union européenne ?",
                answer = "Le drapeau bleu aux 12 étoiles d'or disposées en cercle (symbole de plénitude et d'égalité), l'hymne européen (L'Ode à la joie de Beethoven), la devise 'Unie dans la diversité', la monnaie unique (Euro €) et la Journée de l'Europe (9 mai).",
                dateTip = "💡 Tips mémorisation : Les 12 étoiles ne représentent pas le nombre de pays, mais la perfection et l'harmonie (12 mois de l'année, 12 heures) !",
                isCrucial = true,
                keywords = listOf("12 étoiles", "Ode à la joie", "Unie dans la diversité", "9 mai")
            ),
            GuideQuestion(
                id = 714,
                numberText = "Question 714",
                chapter = CivicChapter.EUROPE,
                question = "Qu'est-ce que l'espace Schengen ?",
                answer = "C'est un espace sans frontières intérieures permettant la libre circulation des personnes entre ses pays membres. Il comprend la plupart des pays de l'UE, ainsi que des pays associés non membres de l'UE comme la Suisse, la Norvège et l'Islande.",
                dateTip = "💡 Tips mémorisation accords de Schengen : 1985 (signés dans le village de Schengen au Luxembourg) !",
                isCrucial = true,
                keywords = listOf("Schengen", "Libre circulation", "Frontières", "Suisse")
            ),
            GuideQuestion(
                id = 720,
                numberText = "Question 720",
                chapter = CivicChapter.EUROPE,
                question = "Où siègent les principales institutions européennes ?",
                answer = "Bruxelles (Belgique) pour la Commission européenne et le Conseil européen ; Strasbourg (France) pour le Parlement européen (séances plénières) ; Luxembourg pour la Cour de justice de l'UE ; Francfort (Allemagne) pour la Banque Centrale Européenne (BCE).",
                dateTip = "💡 Tips mémorisation : Strasbourg est la capitale parlementaire européenne en France !",
                isCrucial = true,
                keywords = listOf("Bruxelles", "Strasbourg", "Luxembourg", "Francfort")
            )
        )
    }

    fun getQuestionsByChapter(chapter: CivicChapter): List<GuideQuestion> {
        return allQuestions.filter { it.chapter == chapter }
    }

    fun getCrucialQuestions(): List<GuideQuestion> {
        return allQuestions.filter { it.isCrucial }
    }

    fun getQuestionsWithDateTips(): List<GuideQuestion> {
        return allQuestions.filter { !it.dateTip.isNullOrBlank() }
    }
}
