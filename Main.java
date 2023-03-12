
/**
 * 
 * Code Permanent: BOUZ90340206
 * Courriel: fg591955@uqam.ca
 * @author Zakariae Bouargan
 * @version 8/03/2023
 */


public class Main {
	
	static String swap(String str, int i, int j)
    {
        if (j == str.length() - 1)
            return str.substring(0, i) + str.charAt(j)
                + str.substring(i + 1, j) + str.charAt(i);
 
        return str.substring(0, i) + str.charAt(j)
            + str.substring(i + 1, j) + str.charAt(i)
            + str.substring(j + 1, str.length());
    }
	
	public static String PermutationInterieure(int Cle, String Message)
	{	
		
		return "";
	}
	
	public static String RotationDroite(String nbr, String Message) // nbr est une variable qui designe le nombre de rotations
	{	
		int max = Integer.parseInt(nbr);
		System.out.println(max);
		
		for(int i=0 ; i<max ; i++)
		{
			for(int j=0 ; j<Message.length()-1 ; j++)
				Message = swap(Message,j,Message.length()-1);
			
		}
		return Message;
	}
	
	public static String RotationGauche(String nbr,String Message)
	{
		int max = Integer.parseInt(nbr);
		System.out.println(max);
		
		for(int i=0 ; i<max ; i++)
		{

			for(int j=0 ; j<Message.length()-1 ; j++)
			{
				if(j==0)
					Message = swap(Message,j,Message.length()-1);
				else
					Message = swap(Message,j-1,j);
			}
			
		}
		
		return Message;
	}
	
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
		
		Message = Clavier.lireString();
		
		if(Cle.length()%4 != 0)
		{
			System.out.println("ERREUR, Cle invalide ! Recommencez...");
		}
		
		while(i < Cle.length()-1)
		{
			if(Cle.charAt(i) == 'P' && Cle.charAt(i+1) == 'I')
			{
				//Message = PermutationInterieure(Cle,Message);
			}
			else if(Cle.charAt(i) == 'I' && Cle.charAt(i+1) == 'V')
			{
				//Message = Inversion(Cle,Message);
			}
			else if(Cle.charAt(i) == 'P' && Cle.charAt(i+1) == 'E')
			{
				//Message = PermutationExterieure(Cle,Message);
			}
			else if(Cle.charAt(i) == 'R' && Cle.charAt(i+1) == 'D')
			{
				Message = RotationDroite(Cle.substring(i+2,i+4),Message);			
			}
			else if(Cle.charAt(i) == 'R' && Cle.charAt(i+1) == 'G')
			{
				Message = RotationGauche(Cle.substring(i+2,i+4),Message);
			}
			i = i + 4;
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
		String Message;
		
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
					Message = cryptage();
					System.out.println(Message);
					
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
