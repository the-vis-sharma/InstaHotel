package com.instahotel;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		int fLM=0, fSM=0, fS=0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Amount of Recharge: ");
		int R = sc.nextInt();
		System.out.println("Is there anything Free with this recharge?");
		System.out.println(" 1. Local mins\n 2. STD mins\n 3. SMS\n 4. Nothing");
		int choice = sc.nextInt();
		switch(choice) {
		case 1: System.out.println("How many Local mins you got?");
				fLM = sc.nextInt();
				break;
		case 2: System.out.println("How many STD mins you got?");
				fSM = sc.nextInt();
				break;
		case 3: System.out.println("How many sms you got?");
				fS = sc.nextInt();
				break;
		case 4: break;
		default: System.out.println("Invalid choice...");
		}
		System.out.println("Tell us about your tariff plan: ");
		System.out.println("how much is your local call rate (per mins)?");
		float lCR = sc.nextFloat();
		System.out.println("how much is your STD call rate (per mins)?");
		float sCR = sc.nextFloat();
		System.out.println("how much is your SMS rate (per SMS)?");
		float sR = sc.nextFloat();
		float hLC = R/lCR + fLM;
		float hSC = R/sCR + fSM;
		float hS = R/sR + fS;
		if(hLC>hSC && hLC>hS)
			System.out.println("You can use "+hLC+" mins with local calls.");
		else if(hSC>hLC && hSC>hS)
			System.out.println("You can use "+hSC+" mins with STD calls.");
		else
			System.out.println("You can use "+hS+" sms.");
	}
}