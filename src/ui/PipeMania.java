package ui;

import model.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;


public class PipeMania {

	private Controller controller = new Controller();

	private Scanner sc = new Scanner(System.in);

	

	public PipeMania(){}

	public static void main(String[] args) {

		PipeMania main = new PipeMania();
		
		System.out.println("\n\n\nInicializando....");

		main.menu();

	}

	public void menu() {

		int option = 0;

		do{System.out.println(
				"\n\n\nSeleccione una opcion\n" +
				"(1) Nueva partida\n" +
				"(2) Ver puntaje\n" +
                "(3) Para Salir\n"
				);
			try{
				option= sc.nextInt();
				sc.nextLine();
				executeOperation(option);
			}
			catch (InputMismatchException e){
				System.out.println("\nInstruccion invalida.");
			}
		    
        }while(option!=3);
	}

	public void executeOperation(int operation) {
		
		
		
		switch(operation) {
		case 1:

			System.out.println("\nIngrese su nickname\n");

			String nickname = sc.next();
            
			controller.initialize(nickname);
			
			innerMenu(nickname);

			break;
		case 2:
            System.out.println(controller.printScores());
			break;
        case 3:
            System.out.println("\nBye!:D");
            break;
		default:
			System.out.println("\nError, opción no válida\n");
			break;

		}
	}

	public void innerMenu(String nickname){

		int option;
		boolean m = true;

		do{
			System.out.println("\n\n\n" + controller.printGrid());
			System.out.println(
				"\n\nSeleccione una opcion\n" +
				"(1) Poner tuberia\n" +
				"(2) Simular\n" +
                "(3) Para Salir\n"
				);
			try{
				option= sc.nextInt();
				sc.nextLine();
				m=innerExecuteOperation(option, nickname);
			}
			catch (InputMismatchException e){
				System.out.println("\nInstruccion invalida.");
			}
		    
        }while(m);

	}

		
	public boolean innerExecuteOperation(int operation, String nickname) {		
		String holder = null, coordinates = "";
		int row=0, col=0, pipeType=0;
		boolean n = true, m = true;
		switch(operation) {
			case 1:
				while (n){
					System.out.println("\n\n¿En que posicion desea ponerla? (ingrese las coordenadas con el formato 'x,y')\n");
					if((coordinates = sc.next()).length()==3){
						try{
							row = Integer.parseInt(coordinates.charAt(0) + "");
							col = Integer.parseInt(coordinates.charAt(2) + "");
							n = false;
						}
						catch (NumberFormatException e){
							System.out.println("\nCoordenadas invalidas, intente de nuevo.\n");
						}
					}
					else{
						System.out.println("\nCoordenadas invalidas, intente de nuevo.");
					}
					
				}

				n = true;

				while(n){
					System.out.println("\n\n¿Que tipo de tuberia desea colocar?\n1)=\n2)| |\n3)O\n");
					try{
						pipeType = sc.nextInt();
						pipeType+=2;
						if(pipeType<3||pipeType>5) System.out.println("\nTipo de tuberia invalido, intente de nuevo.\n");
						else n = false;
					}
					catch (InputMismatchException e){
						System.out.println("\nTipo de tuberia invalido, Intehte de nuevo.\n");
					}
				}

				if(!controller.play(row, col, pipeType)) System.out.println("\nInstruccion invalida\n");

				break;
			case 2:
				holder = controller.simulate();

				if(holder!=null){
					System.out.println("\n\n\n"+holder);
					m = false;
				}
				else System.out.println("\nSolucion incompleta\n");
				break;
			case 3:
				System.out.println("\nRegresando al menu\n");
				m = false;
				break;
			default:
				System.out.println("\nError, opción no válida\n");
			
		}
		return m;
	}

}