
/**
 * 
 * Code Permanent: BOUZ90340206
 * Courriel: fg591955@uqam.ca
 * @author Zakariae Bouargan
 * @version 8/03/2023
 */


public class Main {
	
	public static String cryptage()
	{
		int i = 0;
		String Cle,Message;
		
		System.out.println("++++++++++++\n"
				+ "+ CRYPTAGE +\n"
				+ "++++++++++++");
		
		System.out.println("Cle DE CRYPTAGE : ");
		Cle = Clavier.lireString();
		Cle.toUpperCase();
		
		if(Cle.length()%4 != 0)
		{
			System.out.println("ERREUR, Cle invalide ! Recommencez...");
		}
		
		while(i < Cle.length()/4)
		{
			if(Cle.charAt(0) == 'P' && Cle.charAt(1) == 'I')
			{
				Message = PermutationInterieure(Cle,Message);
			}
			else if(Cle.charAt(0) == 'I' && Cle.charAt(1) == 'V')
			{
				Message = Inversion(Cle,Message);
			}
			else if(Cle.charAt(0) == 'P' && Cle.charAt(1) == 'E')
			{
				Message = PermutationExterieure(Cle,Message);
			}
			else if(Cle.charAt(0) == 'R' && Cle.charAt(1) == 'D')
			{
				Message = RotationDroite(Cle,Message);			
			}
			else if(Cle.charAt(0) == 'R' && Cle.charAt(1) == 'G')
			{
				Message = RotationGauche(Cle,Message);
			}
			i++;
		}
		return Message;
	}
	
	public static void menu()
	{
		System.out.println("----\n"
				+ "MENU\n"
				+ "----\n"
				+ "1. Crypter un message\n"
				+ "2. Decrypter un message\n"
				+ "3. Quitter\n"
				+ "Entrez votre choix :");
	}
	
	public static void main(String[] args)
	{
		char choix;
		
		System.out.println("Ce logiciel permet de crypter et de decrypter des messages secrets.\n"
				+ "\n"
				+ "Tapez <ENTREE> pour continuer...");
		Clavier.lireFinLigne();
		do
		{
			menu();
			
			choix = Clavier.lireCharLn();
			
			switch(choix)
			{
				case '1':
					cryptage();
					
					break;
					
				case '2':
					
					break;
					
				case '3':
					System.out.println("Vous avez décidé de quitter.");
					break;
										
				default: 
					System.out.println("ERREUR, choix de menu invalide ! Recommencez...");
			}
			
			
		}while(choix != '3');
		
	}

	
}
