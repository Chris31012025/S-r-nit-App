package com.example.data

object LivretDuCitoyenRepository {

    val historicDates: List<HistoricDateMnemonic> = listOf(
        HistoricDateMnemonic(
            id = 1,
            year = 1789,
            exactDate = "14 juillet 1789",
            title = "Prise de la Bastille & Déclaration des Droits de l'Homme",
            period = HistoricPeriod.ANCIEN_REGIME_REVOLUTION,
            summary = "Prise de la forteresse royale de la Bastille par le peuple parisien, marquant la fin de la monarchie absolue et le début de la Révolution française. Adoption le 26 août 1789 de la Déclaration des Droits de l'Homme et du Citoyen.",
            mnemonicTip = "Les chiffres se suivent après le 1 : 1 -> 7, 8, 9 ! Impossible d'oublier 1789.",
            officialContext = "Question fondamentale : origine de la Fête nationale (loi de 1880) et texte fondateur de la devise républicaine.",
            tags = listOf("Révolution", "Bastille", "DDHC", "Fête Nationale")
        ),
        HistoricDateMnemonic(
            id = 2,
            year = 1792,
            exactDate = "21 septembre 1792",
            title = "Proclamation de la Première République",
            period = HistoricPeriod.ANCIEN_REGIME_REVOLUTION,
            summary = "Au lendemain de la victoire de Valmy, la Convention nationale proclame l'abolition de la royauté en France et fonde la Première République.",
            mnemonicTip = "Le 21 septembre : 21 ans comme l'âge historique de la majorité civique, année 1792 (92 = 2e grande étape révolutionnaire).",
            officialContext = "Date de naissance du régime républicain en France.",
            tags = listOf("Première République", "Valmy", "Convention")
        ),
        HistoricDateMnemonic(
            id = 3,
            year = 1804,
            exactDate = "21 mars 1804",
            title = "Promulgation du Code Civil (Code Napoléon)",
            period = HistoricPeriod.ANCIEN_REGIME_REVOLUTION,
            summary = "Unification du droit français sous Napoléon Bonaparte, garantissant l'égalité de tous devant la loi, la laïcisation de l'état civil et la liberté contractuelle.",
            mnemonicTip = "1804 : 1800 + 4, 4 piliers du droit civil moderne (famille, propriété, contrats, égalité).",
            officialContext = "Pilier du système juridique français actuel.",
            tags = listOf("Code Civil", "Napoléon", "Droit")
        ),
        HistoricDateMnemonic(
            id = 4,
            year = 1848,
            exactDate = "27 avril 1848",
            title = "Abolition définitive de l'esclavage & Deuxième République",
            period = HistoricPeriod.XIX_SIECLE,
            summary = "Sous la Seconde République, décret rédigé par Victor Schœlcher proclamant l'abolition définitive de l'esclavage dans toutes les colonies françaises et instauration du suffrage universel masculin.",
            mnemonicTip = "1848 : Le chiffre 8 apparaît 2 fois (1848). '8' fait penser à deux maillons de chaînes d'esclave qui se détachent et s'ouvrent !",
            officialContext = "Question très fréquente en préfecture sur Victor Schœlcher et les valeurs de liberté et d'égalité.",
            tags = listOf("Schœlcher", "Esclavage", "Deuxième République", "Suffrage")
        ),
        HistoricDateMnemonic(
            id = 5,
            year = 1870,
            exactDate = "4 septembre 1870",
            title = "Proclamation de la Troisième République",
            period = HistoricPeriod.XIX_SIECLE,
            summary = "Après la chute du Second Empire à Sedan, Léon Gambetta proclame la IIIe République, qui durera jusqu'en 1940 et enracinera définitivement les libertés fondamentales.",
            mnemonicTip = "1870 : Année charnière (70). 7 décennies après 1800 pour installer durablement la République en France.",
            officialContext = "Régime fondateur de l'école laïque, des syndicats et des lois sur la presse.",
            tags = listOf("Troisième République", "Gambetta", "Démocratie")
        ),
        HistoricDateMnemonic(
            id = 6,
            year = 1881,
            exactDate = "1881 - 1882",
            title = "Lois Jules Ferry (École laïque, gratuite et obligatoire)",
            period = HistoricPeriod.XIX_SIECLE,
            summary = "Instauration de l'école primaire gratuite (1881), puis laïque et obligatoire pour les enfants de 6 à 13 ans (1882), sous l'impulsion de Jules Ferry.",
            mnemonicTip = "1881-1882 : Les 'deux 8' qui se suivent (81, 82), comme les 2 principes cardinaux de l'école de la République : laïque & gratuite.",
            officialContext = "Clé de voûte de l'assimilation républicaine et de l'égalité des chances.",
            tags = listOf("Jules Ferry", "École", "Laïcité", "Éducation")
        ),
        HistoricDateMnemonic(
            id = 7,
            year = 1905,
            exactDate = "9 décembre 1905",
            title = "Loi de Séparation des Églises et de l'État",
            period = HistoricPeriod.XIX_SIECLE,
            summary = "Aristide Briand fait voter la loi instaurant la laïcité : « La République assure la liberté de conscience. Elle garantit le libre exercice des cultes [...]. La République ne reconnaît, ne salarie ni ne subventionne aucun culte. »",
            mnemonicTip = "1905 : 9 décembre (9 = mois de la rentrée/école laïque). Le '05' pour les 5 lettres du mot ÉTAT ou CULTE.",
            officialContext = "Question incontournable : la laïcité est le premier principe républicain évalué en entretien.",
            tags = listOf("Laïcité", "1905", "Liberté de conscience", "Briand")
        ),
        HistoricDateMnemonic(
            id = 8,
            year = 1918,
            exactDate = "11 novembre 1918",
            title = "Armistice de la Première Guerre Mondiale",
            period = HistoricPeriod.GUERRES_MONDIALES,
            summary = "Signature de l'armistice mettant fin aux combats de la Grande Guerre (1914-1918). Jour du souvenir national et d'hommage à tous les morts pour la France.",
            mnemonicTip = "11 novembre à 11 heures : Que des 11 ! (11/11 à 11h). Symbole absolu de la fin des hostilités.",
            officialContext = "Jour férié national et commémoration au monument aux morts.",
            tags = listOf("Armistice", "11 novembre", "14-18", "Mémoire")
        ),
        HistoricDateMnemonic(
            id = 9,
            year = 1940,
            exactDate = "18 juin 1940",
            title = "Appel du Général de Gaulle à la Résistance",
            period = HistoricPeriod.GUERRES_MONDIALES,
            summary = "Depuis Londres sur la BBC, le général Charles de Gaulle exhorte les officiers et soldats français à refuser l'armistice avec l'Allemagne nazie et à poursuivre la lutte.",
            mnemonicTip = "18 juin : 18 = majorité civique. Le jour où la France Libre prend son destin en main.",
            officialContext = "Symbole du courage et de l'esprit républicain contre l'oppression.",
            tags = listOf("De Gaulle", "Appel du 18 juin", "Résistance", "Londres")
        ),
        HistoricDateMnemonic(
            id = 10,
            year = 1944,
            exactDate = "21 avril 1944",
            title = "Droit de vote accordé aux femmes",
            period = HistoricPeriod.GUERRES_MONDIALES,
            summary = "Ordonnance signée à Alger par le Gouvernement provisoire du général de Gaulle : « Les femmes sont électrices et éligibles dans les mêmes conditions que les hommes ». Premier vote le 29 avril 1945.",
            mnemonicTip = "1944 : L'année du Débarquement en France et de la Libération. Double '4' (44) = égalité des 2 sexes (femmes et hommes).",
            officialContext = "Principe constitutionnel d'égalité femmes-hommes.",
            tags = listOf("Vote des femmes", "1944", "Égalité", "Suffrage")
        ),
        HistoricDateMnemonic(
            id = 11,
            year = 1945,
            exactDate = "8 mai 1945",
            title = "Victoire des Alliés (Fin de la Seconde Guerre en Europe)",
            period = HistoricPeriod.GUERRES_MONDIALES,
            summary = "Capitulation sans condition de l'Allemagne nazie face aux Alliés et à la France combattante. Jour férié de célébration de la paix et de la liberté.",
            mnemonicTip = "8 mai : 8 = l'infini de la paix retrouvée. Mai = le printemps de la liberté.",
            officialContext = "Jour férié national et commémoration de la Libération.",
            tags = listOf("8 mai 1945", "Victoire", "Libération", "Paix")
        ),
        HistoricDateMnemonic(
            id = 12,
            year = 1957,
            exactDate = "25 mars 1957",
            title = "Traité de Rome (Naissance de la CEE)",
            period = HistoricPeriod.VE_REPUBLIQUE,
            summary = "Création de la Communauté Économique Européenne par les six pays fondateurs : France, Allemagne de l'Ouest, Italie, Belgique, Pays-Bas et Luxembourg.",
            mnemonicTip = "1957 : 57 = 5 + 7 = 12, comme les 12 étoiles du futur drapeau européen.",
            officialContext = "Fondation de la construction européenne dans laquelle s'inscrit la France.",
            tags = listOf("Traité de Rome", "Europe", "CEE", "Fondateurs")
        ),
        HistoricDateMnemonic(
            id = 13,
            year = 1958,
            exactDate = "4 octobre 1958",
            title = "Promulgation de la Constitution de la Ve République",
            period = HistoricPeriod.VE_REPUBLIQUE,
            summary = "Adoption par référendum et promulgation de la Constitution actuelle de la République française rédigée sous la direction de Charles de Gaulle et Michel Debré.",
            mnemonicTip = "Le chiffre 5 en pilier central : 5 - 1 = 4 (le jour) / (5 x 2) = 10 (le mois d'octobre) / 1958 (l'année). Formule : 4 / 10 / 1958 !",
            officialContext = "Question capitale : c'est notre Constitution actuelle régissant toutes les institutions françaises.",
            tags = listOf("Ve République", "Constitution", "De Gaulle", "Institutions")
        ),
        HistoricDateMnemonic(
            id = 14,
            year = 1962,
            exactDate = "28 octobre 1962",
            title = "Élection du Président au Suffrage Universel Direct",
            period = HistoricPeriod.VE_REPUBLIQUE,
            summary = "Référendum voulu par le général de Gaulle modifiant la Constitution pour que le Président soit élu directement par l'ensemble des citoyens, et non plus par un collège restreint.",
            mnemonicTip = "1962 : 62 = 6/2 = 3. Le Président devient la 3e force démocratique directe avec l'Assemblée et le peuple.",
            officialContext = "Mode d'élection actuel du Président de la République.",
            tags = listOf("Suffrage universel", "Président", "Référendum", "1962")
        ),
        HistoricDateMnemonic(
            id = 15,
            year = 1975,
            exactDate = "17 janvier 1975",
            title = "Loi Veil (Dépénalisation de l'IVG)",
            period = HistoricPeriod.VE_REPUBLIQUE,
            summary = "Promulgation de la loi dépénalisant l'Interruption Volontaire de Grossesse, portée avec courage par Simone Veil, ministre de la Santé sous la présidence de Valéry Giscard d'Estaing.",
            mnemonicTip = "Le 'V' de 1975 : 'V' pour la ministre Simone VEIL et 'V' pour le président VALÉRY Giscard d'Estaing !",
            officialContext = "Grande avancée des droits des femmes et de la santé publique en France.",
            tags = listOf("Simone Veil", "IVG", "1975", "Droits des femmes")
        ),
        HistoricDateMnemonic(
            id = 16,
            year = 1981,
            exactDate = "9 octobre 1981",
            title = "Abolition de la Peine de Mort en France",
            period = HistoricPeriod.VE_REPUBLIQUE,
            summary = "Promulgation de la loi d'abolition de la peine de mort présentée par Robert Badinter, garde des Sceaux sous le premier mandat de François Mitterrand.",
            mnemonicTip = "1981 : Année de l'alternance présidentielle (élection de François Mitterrand le 10 mai 1981, loi votée à l'automne 1981).",
            officialContext = "Question fréquente : Robert Badinter et l'engagement humaniste de la France.",
            tags = listOf("Badinter", "Peine de mort", "Mitterrand", "Droits humains")
        ),
        HistoricDateMnemonic(
            id = 17,
            year = 1992,
            exactDate = "7 février 1992",
            title = "Traité de Maastricht (Création de l'Union Européenne)",
            period = HistoricPeriod.VE_REPUBLIQUE,
            summary = "Signature du traité fondateur de l'Union européenne, instaurant la citoyenneté européenne, prévoyant la monnaie unique (Euro) et renforçant la coopération politique.",
            mnemonicTip = "1992 : 92 comme les 12 étoiles multipliées et l'Euro qui arrive à la fin des années 90.",
            officialContext = "Origine du passeport et des droits citoyens européens.",
            tags = listOf("Maastricht", "Union Européenne", "Euro", "Citoyenneté")
        ),
        HistoricDateMnemonic(
            id = 18,
            year = 2024,
            exactDate = "8 mars 2024",
            title = "Inscription de l'IVG dans la Constitution",
            period = HistoricPeriod.XXIE_SIECLE,
            summary = "Le Congrès réuni à Versailles adopte la révision constitutionnelle inscrivant à l'article 34 la « liberté garantie à la femme d'avoir recours à une IVG ». La France devient le 1er pays au monde à inscrire ce droit dans sa norme suprême. Scellement officiel le 8 mars, Journée internationale des droits des femmes.",
            mnemonicTip = "Le 8 mars : Journée internationale des droits des femmes ! L'année 2024 : 2 + 0 + 2 + 4 = 8 comme le 8 mars !",
            officialContext = "Actualité constitutionnelle majeure : très souvent demandée pour tester si le candidat suit l'actualité des lois françaises.",
            tags = listOf("IVG", "Constitution", "Article 34", "8 mars", "Versailles")
        ),
        HistoricDateMnemonic(
            id = 19,
            year = 2024,
            exactDate = "26 juillet - 11 août 2024",
            title = "Jeux Olympiques et Paralympiques de Paris 2024",
            period = HistoricPeriod.XXIE_SIECLE,
            summary = "Paris accueille les XXXIIIes Jeux Olympiques d'été exactement 100 ans après ceux de 1924, avec une cérémonie d'ouverture inédite sur la Seine. La France termine à la 5e place mondiale avec un record historique de 64 médailles (16 en or).",
            mnemonicTip = "Exactement 100 ans après 1924 (1924 + 100 = 2024) ! 64 médailles : 8 x 8 = 64.",
            officialContext = "Événement marquant de cohésion nationale, de célébration de la fraternité et du rayonnement de la France.",
            tags = listOf("JO Paris 2024", "Médailles", "Rayonnement", "Seine")
        ),
        HistoricDateMnemonic(
            id = 20,
            year = 2025,
            exactDate = "2025",
            title = "Entrée de Robert Badinter au Panthéon",
            period = HistoricPeriod.XXIE_SIECLE,
            summary = "Hommage de la Nation à l'ancien garde des Sceaux et président du Conseil constitutionnel Robert Badinter (1928-2024), figure du combat pour les droits de l'homme et l'abolition de la peine de mort.",
            mnemonicTip = "Panthéonise en 2025 : 'Aux grands hommes, la patrie reconnaissante'. 2025 = 1 an après son décès en 2024.",
            officialContext = "Question d'actualité civique fréquente sur les grandes personnalités reposant au Panthéon (Voltaire, Rousseau, Hugo, Curie, Veil, Moulin).",
            tags = listOf("Badinter", "Panthéon", "Hommage", "Justice")
        )
    )

    val chapters: List<LivretChapter> = listOf(
        LivretChapter(
            id = "chap_1_principes",
            number = 1,
            title = "Les Principes Fondamentaux & la Devise",
            subtitle = "L'identité républicaine : indivisible, laïque, démocratique et sociale",
            category = LivretCategory.PRINCIPES,
            articles = listOf(
                LivretArticle(
                    title = "Les Quatre Piliers de la République",
                    content = "L'article 1er de la Constitution de 1958 énonce : « La France est une République indivisible, laïque, démocratique et sociale. Elle assure l'égalité devant la loi de tous les citoyens sans distinction d'origine, de race ou de religion. Elle respecte toutes les croyances. Son organisation est décentralisée. »",
                    keyPoints = listOf(
                        "Indivisible : une seule loi sur tout le territoire, unité de la Nation et usage exclusif de la langue française dans l'administration.",
                        "Laïque : stricte neutralité religieuse de l'État, des services publics et de l'école républicaine.",
                        "Démocratique : le pouvoir émane du peuple par le vote et le suffrage universel.",
                        "Sociale : solidarité nationale, sécurité sociale et protection des plus vulnérables."
                    ),
                    officialQuote = "« La France est une République indivisible, laïque, démocratique et sociale. »",
                    legalReference = "Article 1er de la Constitution du 4 octobre 1958",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuC84MQYUECLv5I70282814GxyJDipd6dC7vKpU0OVFE9Y-PK1Oug-PgReBemOIHOHWaqTjFJb8izN8y4oDEmfzyTcfsn4Ey0a--uqdRfF8iWWMtiJVAWJFOFWVLRRFarrObtOeN9V2fdRTjS2fSNU8M-AM5Ki04Q5VcLWcjNElGZTgiIFfzaWnpxwBmb8op1o3SfTz9yLUeYIR6EQpqHhv1HlipUk_LnNe0LbSfRWXrrusBb_nyaq4HVA"
                ),
                LivretArticle(
                    title = "La Devise Républicaine : Liberté, Égalité, Fraternité",
                    content = "Héritée de la Révolution de 1789, la devise est inscrite au fronton de toutes les mairies, écoles et édifices publics de France.\n\n• Liberté : pouvoir faire tout ce qui ne nuit pas à autrui (liberté d'opinion, d'expression, de circulation, de culte).\n• Égalité : les mêmes droits et les mêmes devoirs pour tous, sans privilège ni discrimination. Égalité stricte entre les femmes et les hommes.\n• Fraternité : la solidarité citoyenne, l'entraide mutuelle et le refus de l'exclusion.",
                    keyPoints = listOf(
                        "Liberté d'expression et de conscience dans le respect de l'ordre public.",
                        "Égalité parfaite entre femmes et hommes : dans l'emploi, la famille et la société.",
                        "Fraternité vivante par l'impôt redistributif et l'engagement associatif."
                    ),
                    officialQuote = "« Liberté, Égalité, Fraternité »",
                    legalReference = "Article 2 de la Constitution"
                ),
                LivretArticle(
                    title = "La Laïcité Républicaine au Quotidien",
                    content = "La laïcité garantit la liberté de croire ou de ne pas croire. Elle sépare le politique du religieux. L'État ne privilégie aucune religion et n'en persécute aucune.\n\nDans l'espace public, chacun est libre d'exprimer ses convictions dans les limites de la loi. En revanche, les agents du service public doivent faire preuve d'une neutralité absolue (pas de signes religieux ostensibles). À l'école publique, le port de signes religieux ostensibles est interdit pour protéger les élèves de toute pression.",
                    keyPoints = listOf(
                        "Liberté de conscience absolue : droit d'avoir une religion, d'en changer ou de n'en avoir aucune.",
                        "Neutralité du service public : aucun agent de l'État ne peut afficher ses convictions.",
                        "Protection des mineurs : respect de la Charte de la laïcité à l'école."
                    ),
                    legalReference = "Loi du 9 décembre 1905 & Loi du 15 mars 2004"
                )
            ),
            relatedDateIds = listOf(1, 7, 13)
        ),
        LivretChapter(
            id = "chap_2_symboles",
            number = 2,
            title = "Les Symboles de la République Française",
            subtitle = "Les emblèmes reconnus par la Constitution et l'Histoire",
            category = LivretCategory.SYMBOLES,
            articles = listOf(
                LivretArticle(
                    title = "Le Drapeau Tricolore & L'Hymne National",
                    content = "• Le drapeau bleu, blanc, rouge : né sous la Révolution en juillet 1789. Le blanc était la couleur de la royauté, encadré par le bleu et le rouge, couleurs traditionnelles de la ville de Paris.\n• L'hymne national, « La Marseillaise » : composé à Strasbourg en 1792 par Rouget de Lisle sous le titre de 'Chant de guerre pour l'armée du Rhin'. Devenu hymne national sous la IIIe République en 1879.",
                    keyPoints = listOf(
                        "Le drapeau est l'unique emblème national fixé à l'article 2 de la Constitution.",
                        "La Marseillaise est entonnée lors des cérémonies officielles et des victoires sportives de la France."
                    ),
                    legalReference = "Article 2 de la Constitution de 1958"
                ),
                LivretArticle(
                    title = "Marianne, le 14 Juillet et le Coq Gaulois",
                    content = "• Marianne : incarnation féminine de la République et de la Liberté. Coiffée du bonnet phrygien (symbole d'affranchissement des esclaves sous l'Antiquité), son buste est présent dans toutes les mairies de France.\n• Le 14 Juillet (Fête Nationale) : commémore à la fois la prise de la Bastille de 1789 et la Fête de la Fédération du 14 juillet 1790 (union de tous les Français).\n• Le Coq gaulois : symbole de bravoure et de vigilance depuis l'Antiquité (en latin 'gallus' signifie à la fois coq et gaulois).",
                    keyPoints = listOf(
                        "Marianne figure sur les timbres-poste et les pièces d'euro françaises.",
                        "La Fête nationale donne lieu au défilé militaire sur les Champs-Élysées et aux feux d'artifice populaires."
                    )
                )
            ),
            relatedDateIds = listOf(1, 2)
        ),
        LivretChapter(
            id = "chap_3_droits_devoirs",
            number = 3,
            title = "Les Droits & les Devoirs du Citoyen",
            subtitle = "La Charte d'adhésion pour devenir Français",
            category = LivretCategory.DROITS_DEVOIRS,
            articles = listOf(
                LivretArticle(
                    title = "Les Droits Fondamentaux Garantis",
                    content = "Devenir citoyen français confère la plénitude des droits civiques et politiques :\n• Le droit de vote et d'éligibilité à toutes les élections démocratiques.\n• La liberté d'expression, d'opinion et de presse.\n• La liberté de circulation et d'établissement en France et dans l'Union européenne.\n• Le droit à la protection de la santé, à l'éducation gratuite et à la sécurité sociale.\n• La protection consulaire française partout dans le monde.",
                    keyPoints = listOf(
                        "Vote citoyen : suffrage universel direct et secret.",
                        "Égalité salariale et protection contre toutes les discriminations.",
                        "Accès aux emplois de la fonction publique d'État et territoriale."
                    )
                ),
                LivretArticle(
                    title = "Les Devoirs Indispensables envers la Nation",
                    content = "La citoyenneté n'est pas seulement un faisceau de droits, elle engage des devoirs stricts envers la communauté nationale :\n• Respecter les lois de la République : nul n'est censé ignorer la loi ni s'y soustraire au nom de traditions ou de croyances particulières.\n• Contribuer aux charges publiques : payer ses impôts et cotisations en fonction de ses facultés contributives.\n• Participer à la défense de la Nation : obligation de recensement civique et Journée Défense et Citoyenneté (JDC).\n• Devoir de juré d'assises : tout citoyen tiré au sort doit participer à la justice criminelle.",
                    keyPoints = listOf(
                        "L'impôt est le ciment de la solidarité et du financement des hôpitaux, écoles et routes.",
                        "Primauté absolue des lois civiles républicaines sur toute règle religieuse."
                    ),
                    officialQuote = "« Les citoyens sont égaux devant la loi et doivent concourir ensemble aux charges de l'État. »"
                )
            ),
            relatedDateIds = listOf(1, 4, 10)
        ),
        LivretChapter(
            id = "chap_4_institutions",
            number = 4,
            title = "Les Institutions & Pouvoirs Publics",
            subtitle = "La séparation des pouvoirs exécutif, législatif et judiciaire",
            category = LivretCategory.INSTITUTIONS,
            articles = listOf(
                LivretArticle(
                    title = "Le Pouvoir Exécutif : Président & Gouvernement",
                    content = "• Le Président de la République : Chef de l'État, élu au suffrage universel direct pour un mandat de 5 ans (quinquennat, renouvelable une fois consécutivement). Il réside au Palais de l'Élysée. Il est le chef des armées, garant de la Constitution et nomme le Premier ministre.\n• Le Premier ministre : Chef du Gouvernement, réside à l'Hôtel de Matignon. Il conduit la politique de la Nation et dirige l'action des ministres.",
                    keyPoints = listOf(
                        "Président : peut dissoudre l'Assemblée nationale et soumettre des lois à référendum.",
                        "Gouvernement : responsable devant l'Assemblée nationale qui peut voter une motion de censure."
                    ),
                    legalReference = "Articles 5 à 21 de la Constitution de 1958"
                ),
                LivretArticle(
                    title = "Le Pouvoir Législatif : Le Parlement (Assemblée & Sénat)",
                    content = "Le Parlement français est bicaméral (deux chambres) :\n• L'Assemblée nationale : siège au Palais Bourbon à Paris. Composée de 577 députés élus au suffrage universel direct pour 5 ans. Elle a le dernier mot en cas de désaccord avec le Sénat.\n• Le Sénat : siège au Palais du Luxembourg. Composé de 348 sénateurs élus au suffrage universel indirect pour 6 ans (renouvelé par moitié tous les 3 ans). Il représente les collectivités territoriales.",
                    keyPoints = listOf(
                        "Rôle du Parlement : voter les lois, voter le budget de l'État et contrôler l'action du Gouvernement.",
                        "L'article 49 alinéa 3 (49.3) permet au Gouvernement d'engager sa responsabilité sur un texte."
                    )
                ),
                LivretArticle(
                    title = "Le Conseil Constitutionnel & l'Organisation Territoriale",
                    content = "• Le Conseil constitutionnel : siège au Palais-Royal (9 membres nommés pour 9 ans, plus les anciens Présidents). Il veille à la régularité des élections et s'assure que les lois votées respectent la Constitution.\n• Les échelons territoriaux de la France :\n  - La Commune (près de 35 000 communes, dirigées par un maire et son conseil municipal)\n  - Le Département (101 départements, dont 96 en métropole et 5 d'outre-mer)\n  - La Région (18 régions, dont 13 en métropole et 5 d'outre-mer, chargées de l'économie et des transports)",
                    keyPoints = listOf(
                        "Le maire est à la fois représentant de la commune et agent de l'État (officier d'état civil).",
                        "Les 5 DROM d'outre-mer : Guadeloupe, Martinique, Guyane, La Réunion, Mayotte."
                    )
                )
            ),
            relatedDateIds = listOf(13, 14)
        ),
        LivretChapter(
            id = "chap_5_europe_monde",
            number = 5,
            title = "La France dans l'Europe & dans le Monde",
            subtitle = "Une puissance diplomatique, culturelle et démocratique",
            category = LivretCategory.EUROPE_MONDE,
            articles = listOf(
                LivretArticle(
                    title = "La Construction Européenne et ses 27 États",
                    content = "La France est un des pays fondateurs majeurs de l'Union européenne aux côtés de l'Allemagne, de l'Italie et des pays du Benelux. L'UE compte aujourd'hui 27 États membres (depuis le départ du Royaume-Uni / Brexit en 2020).\n\n• La monnaie unique : l'Euro (€), utilisé par 20 pays de la zone euro.\n• L'espace Schengen : libre circulation des personnes sans contrôle aux frontières intérieures.\n• Les symboles européens : la devise « Unie dans la diversité », le drapeau bleu aux 12 étoiles dorées et l'hymne européen (Ode à la joie de Beethoven).",
                    keyPoints = listOf(
                        "Le Parlement européen siège à Strasbourg (France).",
                        "Tout citoyen français est également citoyen européen."
                    ),
                    legalReference = "Traités de Rome (1957) et de Maastricht (1992)"
                ),
                LivretArticle(
                    title = "Le Rôle International & la Francophonie",
                    content = "La France est l'un des 5 membres permanents du Conseil de Sécurité de l'Organisation des Nations Unies (ONU) avec droit de veto (avec les USA, la Chine, la Russie et le Royaume-Uni). Elle est membre de l'OTAN et puissance nucléaire souveraine.\n\nLa Francophonie rassemble plus de 320 millions de locuteurs français à travers les 5 continents. Le français est l'une des langues officielles des grandes organisations internationales (ONU, CIO, Croix-Rouge, Union postale).",
                    keyPoints = listOf(
                        "5e puissance économique mondiale et première destination touristique de la planète.",
                        "Défense du droit international, des droits humains et des accords climatiques de Paris (COP21)."
                    )
                )
            ),
            relatedDateIds = listOf(12, 17)
        )
    )
}
