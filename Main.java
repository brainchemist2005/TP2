/**
 * Ce programme est un outil de cryptage puissant conçu pour assurer 
 * la sécurité des messages. Avec seulement quelques étapes simples, vous pouvez crypter
 * n'importe quel message de votre choix à l'aide d'une clé de cryptage sécurisée de votre
 * choix. Il vous suffit d'entrer votre message et la clé de cryptage, et
 * le programme s'occupe du reste. Ce programme utilise des algorithmes 
 * de cryptage inversions et permutations pour garantir que votre message est protégé des regards indiscrets,
 * le rendant pratiquement impossible à décoder sans la clé. Avec la meme clé de cryptage vous pouvez decrypter votre message.
 * Que vous cherchiez à envoyer des informations confidentielles ou simplement à garder vos messages privés.
 * @author Zakariae Bouargan
 * Code Permanent: BOUZ90340206
 * Courriel: fg591955@uqam.ca
 * Cours : INF1120
 * @version 22/03/2023
 */

public class Cryptage {

    public static final char MAX  = '9', MIN = '0', PERMUTATION = 'P', ROTATION = 'R', INVERSION = 'I', INVERSION1 = 'V',
            DROITE = 'D', GAUCHE = 'G', INTERIEURE = 'I', EXTERIEURE = 'E';
    public static String MENU = "----\nMENU\n----\n1. Crypter un message\n2. Decrypter un message\n3. Quitter\n" +
            "\nEntrez votre choix :", MSG_ERREUR_CLE = "ERREUR, cle invalide ! Recommencez...",
            ENTREE = "Tapez <ENTREE> pour continuer...", ERREUR_MENU = "\nERREUR, choix de menu invalide ! Recommencez...\n",
            MSG_DECRYPTAGE = "MESSAGE A DECRYPTER (ENTREE pour annuler) : ", CLE = "CLE DE CRYPTAGE : ",
            MSG_CRYPTAGE = "MESSAGE A CRYPTER (ENTREE pour annuler) : ", ANNULATION = "--> OPERATION ANNULEE <--",
            PRESENTATION = "Ce logiciel permet de crypter et de decrypter des messages secrets.\n", CRYPTAGE = "++++++++++++\n CRYPTAGE +\n++++++++++++\n",
            DECRYPTAGE = "++++++++++++++\n+ DECRYPTAGE +\n++++++++++++++\n", MSG_ERREUR = "ERREUR, message invalide :\n\tLe message ne peut contenir que des lettres, des chiffres, des\n\tespaces, et les caracteres .!?,;:'-\"";



    /**
     * Cette fonction verifie si le message est valide.
     *
     * @param msg est le string string a verifie
     * @return true si la cle est valide sinon false
     */
    public static boolean validation(String msg)
    {
        boolean status = true;
        int i=0;

        if (msg == "") {
            status = true;
        }

        else {
            while (status) {
                if (!((msg.charAt(i) >= 'a' && msg.charAt(i) <= 'z') || (msg.charAt(i) >= 'A' && msg.charAt(i) <= 'Z') || (msg.contains(".") || msg.contains(";") || msg.contains("!") || msg.contains("?") || msg.contains(",") || msg.contains(":") || msg.contains("-") || msg.contains("'") || msg.contains("\"")))) {
                    status = false;
                }
                i++;
            }
        }

        return status;
    }


    /**
     * Cette fonction verifie si la cle est valide.
     *
     * @param cle est le string string a verifie
     * @return true si la cle est valide sinon false
     */
    public static boolean verification(String cle) {
        int i = 0;

        if (cle.length() % 4 != 0 || cle.length() == 0)  { //Cette condition verifie si la cle est un multiple
                                                            // de 4 ça veut dire on ne peut pas avoir RD0 ou RD05P
            return false;
        } else {
            while (i < cle.length()) {
                if ((cle.charAt(i) == PERMUTATION && cle.charAt(i + 1) == EXTERIEURE) ||
                        (cle.charAt(i) == PERMUTATION && cle.charAt(i + 1) == INTERIEURE) ||
                        (cle.charAt(i) == INVERSION && cle.charAt(i + 1) == INVERSION1) ||
                        (cle.charAt(i) == ROTATION && cle.charAt(i + 1) == GAUCHE) ||
                        (cle.charAt(i) == ROTATION && cle.charAt(i + 1) == DROITE)) {
                    i += 2; //j'augmente par deux pas, car j'ai deja checker le charactere i+1
                } else if (cle.charAt(i) <= MAX && cle.charAt(i) >= MIN &&
                        cle.charAt(i + 1) <= MAX && cle.charAt(i + 1) >= MIN) {
                    i += 2;
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Échange deux caractères dans une chaîne de caractères.
     * @param str la chaîne de caractères dans laquelle effectuer l'échange
     * @param i l'indice du premier caractère à échanger
     * @param j l'indice du deuxième caractère à échanger
     * @return une nouvelle chaîne de caractères avec les caractères aux indices spécifiés échangés
     */
    static String swap(String str, int i, int j) {
        // Si j est le dernier indice, échanger les caractères sans la dernière sous-chaîne

        if (j == str.length() - 1)
            return str.substring(0, i) + str.charAt(j) +
                    str.substring(i + 1, j) + str.charAt(i);

        // Sinon, échanger les caractères avec la dernière sous-chaîne incluse

        return str.substring(0, i) + str.charAt(j) +
                str.substring(i + 1, j) + str.charAt(i) +
                str.substring(j + 1);
    }

    /**
     * La méthode PermutationExterieure effectue une permutation externe de
     * la chaîne de caractères message. Le nombre de permutations à effectuer
     * est spécifié par le paramètre nbr.
     * @param nbr un String qui représente le nombre de rotations à effectuer
     * @param message un String qui représente la chaîne de caractères à faire pivoter
     * @return un String qui est la chaîne de caractères résultante après la permutation
     */
    public static String permutationInterieure(String nbr, String message) {
        int max = Integer.parseInt(nbr);
        int j;
        int i;

        if (message.length() < 2)
            return message;

        else {
            if (message.length() % 2 == 0) {
                i = ((message.length()) / 2) - 1;
                j = i + 1;
            } else {
                i = message.length() / 2;
                j = i;
                i--;
                j++;
            }

            for (int c = 0; c < max; c++, j++, i--) {
                if (j == message.length()) {
                    if (message.length() % 2 == 0) {
                        j = (message.length()) / 2;
                        i = j - 1;
                    } else {
                        i = message.length() / 2;
                        j = i;
                        i--;
                        j++;
                    }

                }

                message = swap(message, i, j);

            }
        }

        return message;
    }

    /**
     * La méthode PermutationExterieure effectue une permutation externe de
     * la chaîne de caractères message. Le nombre de permutations à effectuer
     * est spécifié par le paramètre nbr.
     * @param nbr un String qui représente le nombre de rotations à effectuer
     * @param message un String qui représente la chaîne de caractères à faire pivoter
     * @return un String qui est la chaîne de caractères résultante après la permutation
     */
    public static String permutationExterieure(String nbr, String message) {
        int max = Integer.parseInt(nbr);

        if (message.length() < 2)
            return message;

        else {
            for (int i = 0, j = message.length() - 1, l = 0; i < max; i++) {
                message = swap(message, l, j);
                l++;
                j--;

                // Si les indices se croisent, on réinitialise les compteurs pour recommencer la permutation externe
                if (l == message.length() / 2) {
                    l = 0;
                    j = message.length() - 1;
                }

            }
        }

        return message;
    }

    /**
     *La méthode Inversion prend en entrée deux arguments : un nombre nbr
     * et une chaîne de caractères message. Elle effectue une inversion
     * des premiers nbr caractères et des derniers nbr caractères de la
     * chaîne de caractères message.
     *
     */
    public static String inversion(String nbr, String message) {
        int max = Integer.parseInt(nbr);
        String Str1;
        String Str2;
        String Str3;
        String Output = "";

        if (max >= message.length() || max == 1) // si la lon
            return message;

        else {
            Str1 = message.substring(0, max);

            for (int i = Str1.length() - 1; i >= 0; i--)
                Output += Str1.charAt(i);


            if (max <= message.length() / 2) {
                Str2 = message.substring(max, message.length() - max);
                Output += Str2;

                Str3 = message.substring(message.length() - max);

                for (int i = Str3.length() - 1; i >= 0; i--)
                    Output += Str3.charAt(i);

            } else {
                Str2 = Output + message.substring(max);

                Str3 = Str2.substring(message.length() - max, message.length());

                Output = Output.substring(0, message.length() - max);

                for (int i = Str3.length() - 1; i >= 0; i--)
                    Output += Str3.charAt(i);


            }

        }

        return Output;
    }

    /**
     * Effectue une rotation droite de la chaîne de caractères donnée
     * @param nbr un String qui représente le nombre de rotations à effectuer
     * @param message un String qui représente la chaîne de caractères à faire pivoter
     * @return un String qui est la chaîne de caractères résultante après la rotation
     */
    public static String rotationDroite(String nbr, String message)
    {
        // max est une variable qui designe le nombre de rotations
        int max = Integer.parseInt(nbr); // parseInt() convertit un String en int

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < message.length() - 1; j++)
                message = swap(message, j, message.length() - 1);

        }
        return message;
    }

    /**
     * Effectue une rotation gauche de la chaîne de caractères donnée
     * @param nbr un String qui représente le nombre de rotations à effectuer
     * @param message un String qui représente la chaîne de caractères à faire pivoter
     * @return un String qui est la chaîne de caractères résultante après la rotation
     */
    public static String rotationGauche(String nbr, String message) {
        int max = Integer.parseInt(nbr);

        for (int i = 0; i < max; i++) {

            for (int j = 0; j < message.length() - 1; j++) {
                if (j == 0)
                    message = swap(message, j, message.length() - 1);
                else
                    message = swap(message, j - 1, j);
            }

        }

        return message;
    }

    /**
     * Crypte le message donne avec la cle de cryptage donnee, et retourne
     * le message crypte
     * ANTECEDENT : la cle et le message doivent etre valides.
     *
     * @param cle la cle de cryptage
     * @param message le message à crypter avec la cle donnee
     * @return le message crypte
     */
    public static String crypter(String cle, String message) {
        int i = 0;
        boolean status = false;

        System.out.println(CRYPTAGE);

        while (!status) {
            System.out.print(CLE);
            cle = Clavier.lireString();
            cle = cle.toUpperCase();

            status = verification(cle);

            if (!status) {
                System.out.println("\n" + MSG_ERREUR_CLE);
            }

        }

        status = false;

        while (!status) {
            System.out.print(MSG_CRYPTAGE);
            message = Clavier.lireString();

            status = validation(message);

            if (message == "")
                System.out.println("\n" + ANNULATION + "\n");

            else if (!status) {
                System.out.println("\n" + MSG_ERREUR);
            }

        }

        while (i < cle.length() - 1) {
            String nbr = cle.substring(i + 2, i + 4);
            if (cle.charAt(i) == PERMUTATION && cle.charAt(i + 1) == INTERIEURE) {
                message = permutationInterieure(nbr, message);
            } else if (cle.charAt(i) == INVERSION && cle.charAt(i + 1) == INVERSION1) {
                message = inversion(nbr, message);
            } else if (cle.charAt(i) == PERMUTATION && cle.charAt(i + 1) == EXTERIEURE) {
                message = permutationExterieure(nbr, message);
            } else if (cle.charAt(i) == ROTATION && cle.charAt(i + 1) == DROITE) {
                message = rotationDroite(nbr, message);
            } else if (cle.charAt(i) == ROTATION && cle.charAt(i + 1) == GAUCHE) {
                message = rotationGauche(nbr, message);
            }

            i = i + 4;
        }

        return message;

    }

    /**
     * Decrypte le message donne avec la cle de cryptage donnee, et retourne
     * le message decrypte.
     * ANTECEDENT : la cle et le message doivent etre valides.
     *
     * @param cle la cle de cryptage
     * @param message le message a decripter avec la cle donnee
     * @return le message decrypte
     */
    public static String decrypter(String cle, String message) {
        String Size;
        boolean status = false;
        int i ;

        System.out.println(DECRYPTAGE);

        while (!status) { // boucle de validation de la cle de cryptage
            System.out.print(CLE);
            cle = Clavier.lireString();
            cle = cle.toUpperCase();

            status = verification(cle);

            if (!status) {
                System.out.println(MSG_ERREUR_CLE);
            }

        }

        status = false;

        while (!status) {
            System.out.print(MSG_CRYPTAGE);
            message = Clavier.lireString();

            status = validation(message);

            if (!status) {
                System.out.println("\n" + MSG_ERREUR);
            }

        }

        if (message == "")
            System.out.println(ANNULATION);

        else { // Application des opérations de décryptage correspondant à la clé sur le message
            i = cle.length()-4;
            while (i >= 0) {
                String nbr = cle.substring(i + 2, i + 4);
                if (cle.charAt(i) == PERMUTATION && cle.charAt(i + 1) == INTERIEURE) {
                    message = permutationInterieure(nbr, message);
                } else if (cle.charAt(i) == INVERSION && cle.charAt(i + 1) == INVERSION1) {
                    Size = message.length() / 2 + "";
                    message = permutationExterieure(Size, message);
                    message = inversion(nbr, message);
                    message = permutationExterieure(Size, message);
                } else if (cle.charAt(i) == PERMUTATION && cle.charAt(i + 1) == EXTERIEURE) {
                    message = permutationExterieure(nbr, message);
                } else if (cle.charAt(i) == ROTATION && cle.charAt(i + 1) == DROITE) {
                    message = rotationGauche(nbr, message);
                } else if (cle.charAt(i) == ROTATION && cle.charAt(i + 1) == GAUCHE) {
                    message = rotationDroite(nbr, message);
                }

                i = i - 4;
            }

        }

        return message;

    }

    /**

     Affiche un menu de choix à l'utilisateur et attend une entrée de sa part.
     */
    public static void menu() {
        System.out.print(MENU);
    }

    public static void main(String[] args) {
        String choix;
        String message = "";
        String cle = "";

        System.out.println(PRESENTATION);
        System.out.println(ENTREE);
        Clavier.lireFinLigne();
        do {
            menu();

            choix = Clavier.lireString();

            switch (choix) {
                case "1":
                    message = crypter(cle, message);
                    if (message != "") {
                        System.out.println("\nMESSAGE CRYPTE : [" + message + "]\n");
                        System.out.println(ENTREE+"\n");
                    }
                    break;

                case "2":
                    message = decrypter(cle, message);
                    if (message != "") {
                        System.out.println("\nMESSAGE DECRYPTE : [" + message + "]\n");
                        System.out.println(ENTREE+"\n");
                        Clavier.lireFinLigne();
                    }
                    break;

                case "3":
                    System.out.println("\nAUREVOIR !");
                    break;

                default:
                    System.out.println(ERREUR_MENU);
            }

            } while (!choix.equals("3")); /// this DOESN'T WORK s.equals();

    }

}
