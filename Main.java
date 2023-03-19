
/**
 * Ce programme est un outil de cryptage puissant conçu pour assurer 
 * la sécurité des messages. Avec seulement quelques étapes simples, vous pouvez crypter 
 * n'importe quel message de votre choix à l'aide d'une clé de cryptage sécurisée de votre 
 * choix. Il vous suffit d'entrer votre message et la clé de cryptage, et 
 * le programme s'occupe du reste. Ce programme utilise des algorithmes 
 * de cryptage inversions et permutations pour garantir que votre message est protégé des regards indiscrets,
 * le rendant pratiquement impossible à décoder sans la clé. Avec la meme clé de cryptage vous pouvez decrypter votre message.
 * Que vous cherchiez à envoyer des informations confidentielles ou simplement à garder vos messages privés.
 *     
 * Code Permanent: BOUZ90340206
 * Courriel: fg591955@uqam.ca
 * @author Zakariae Bouargan
 * @version 8/03/2023
 */


public class Main {
	
	public static int verification(String Cle)
	{
		int i =0;
		
		if(Cle.length() % 4 != 0)
			return 0;
		
		else{
			while(i<Cle.length())
			{			
				 if(Cle.charAt(i) == 'P' && Cle.charAt(i+1) == 'E' || Cle.charAt(i) == 'P' && Cle.charAt(i+1) == 'I' || Cle.charAt(i) == 'I' && Cle.charAt(i+1) == 'V' || Cle.charAt(i) == 'R' && Cle.charAt(i+1) == 'G' || Cle.charAt(i) == 'R' && Cle.charAt(i+1) == 'D')
					i = i+2;
					
				else if(Cle.charAt(i) <= '9' && Cle.charAt(i) >= '0' && Cle.charAt(i+1) <= '9' && Cle.charAt(i+1) >= '0')
					i = i+2;
				
				else
					return 0;
			}
		}
		
		return 1;
		
	}
	
	static String swap(String str, int i, int j)
    {
        if (j == str.length() - 1)
            return str.substring(0, i) + str.charAt(j)
                + str.substring(i + 1, j) + str.charAt(i);
 
        return str.substring(0, i) + str.charAt(j)
            + str.substring(i + 1, j) + str.charAt(i)
            + str.substring(j + 1, str.length());
    }
	
	public static String PermutationInterieure(String nbr, String Message)
	{
		int Max = Integer.parseInt(nbr),j, i;
		
		if(Message.length() < 2)
			return Message;
		
		else
		{
			if(Message.length()%2 == 0)
			{
				i=((Message.length())/2)- 1;
				j = i+1;
			}
						
			else
			{
				i= Message.length()/2;
				j=i;
				i--;
				j++;
			}
			
			for(int c=0; c<Max; c++,j++,i--)
			{	
				if(j == Message.length())
				{				
					if(Message.length()%2 == 0)
					{
						j=(Message.length())/2;
						i = j-1;
					}
					
					else
					{
						i= Message.length()/2;
						j=i;
						i--;
						j++;
					}
					
				}
								
				Message = swap(Message,i,j);				
				
			}
		}
		
		return Message;
	}
	
	public static String PermutationExterieure(String nbr, String Message)
	{	
		int Max = Integer.parseInt(nbr);
		
		if(Message.length() < 2)
			return Message;
		
		else 
		{
			for(int i=0,j=Message.length()-1,l=0 ; i<Max ; i++)
			{
				System.out.println(l + " " + j);
				Message = swap(Message,l,j);
				System.out.print(Message +"\n");
				
				l++;
				j--;
				
				if(l == Message.length()/2)
				{
					l=0;
					j=Message.length()-1;
					System.out.println(l + " Updated " + j);
				}
					
				
				
			}
		}
		
		return Message;
	}
	
	public static String Inversion(String nbr,String Message)
	{
		int Max = Integer.parseInt(nbr);
		String Str1 ,Str2, Str3 = "", Output = "";

		if(Max >= Message.length() || Max == 1)
			return Message;
		
		else
		{
			Str1 = Message.substring(0, Max);
			
			for (int i = Str1.length() - 1; i >= 0; i--) 
			{
			    Output += Str1.charAt(i);
			}
						
			if(Max <= Message.length()/2)
			{
				Str2 = Message.substring(Max,Message.length()-Max);
				Output += Str2;
				
				Str3 = Message.substring(Message.length()-Max,Message.length());
				
				for (int i = Str3.length() - 1; i >= 0; i--) 
				{
				    Output += Str3.charAt(i);
				}
			}
			
			else
			{	
				Str2 = Output + Message.substring(Max,Message.length());
				
				Str3 =  Str2.substring(Message.length()-Max,Message.length());
				
				Output = Output.substring(0,Message.length()-Max);
				
				for (int i = Str3.length() - 1; i >= 0; i--) 
				{
				    Output += Str3.charAt(i);
				}
				
			}			
				
		}
		
		return Output;
	}
	
	public static String RotationDroite(String nbr, String Message) // nbr est une variable qui designe le nombre de rotations
	{	
		int Max = Integer.parseInt(nbr);
		
		for(int i=0 ; i<Max ; i++)
		{
			for(int j=0 ; j<Message.length()-1 ; j++)
				Message = swap(Message,j,Message.length()-1);
			
		}
		return Message;
	}
	
	public static String RotationGauche(String nbr,String Message)
	{
		int Max = Integer.parseInt(nbr);
		
		for(int i=0 ; i<Max ; i++)
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
		int i = 0, status = 0;
		String Cle= "" ,Message;
		
		System.out.println("++++++++++++\n"
				+ "+ CRYPTAGE +\n"
				+ "++++++++++++");
		
		
		
		while(status != 1)
		{
			System.out.println("Cle DE CRYPTAGE : ");
			Cle += Clavier.lireString();
			Cle.toUpperCase();
			
			status = verification(Cle);
		
			
			if(status == 0)
			{
				System.out.println("ERREUR, cle invalide ! Recommencez...");
			}
		
		}
			
			Message = Clavier.lireString();
			
			
			while(i < Cle.length()-1)
			{
				if(Cle.charAt(i) == 'P' && Cle.charAt(i+1) == 'I')
				{
					Message = PermutationInterieure(Cle.substring(i+2,i+4),Message);
				}
				else if(Cle.charAt(i) == 'I' && Cle.charAt(i+1) == 'V')
				{
					Message = Inversion(Cle.substring(i+2,i+4),Message);
				}
				else if(Cle.charAt(i) == 'P' && Cle.charAt(i+1) == 'E')
				{
					Message = PermutationExterieure(Cle.substring(i+2,i+4),Message);
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
	
	public static String decryptage()
	{
		String Message, Cle;
		
		System.out.println("++++++++++++++\n"
				+ "+ DECRYPTAGE +\n"
				+ "++++++++++++++");
		
		System.out.println("CLE DE CRYPTAGE : ");
		Message = Clavier.lireString();
		
		
		
		
		return "";
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
		String Message ;
		
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
					Message = decryptage();	
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
