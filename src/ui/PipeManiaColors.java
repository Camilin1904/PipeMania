package ui;

import model.ControllerColors;

import java.util.InputMismatchException;
import java.util.Scanner;


public class PipeManiaColors {

	private ControllerColors controller = new ControllerColors();

	private Scanner sc = new Scanner(System.in);

	

	public PipeManiaColors(){}

	public static void main(String[] args) {

		PipeManiaColors main = new PipeManiaColors();
        
		System.out.println(ConsoleColors.YELLOW+"\n\n\nInicializando...."+ConsoleColors.RESET);

		main.menu();

	}

	public void menu() {

		int option=0;

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
                System.out.println(ConsoleColors.RED+"\nInstruccion invalida."+ConsoleColors.RESET);
                sc.nextLine();
            }
            
        }while(option!=3);
	}

	public void executeOperation(int operation) throws InputMismatchException {
		
		
		
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
			System.out.println(ConsoleColors.RED+"\nError, opción no válida\n"+ConsoleColors.RESET);
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
				System.out.println(ConsoleColors.RED +"\nInstruccion invalida." + ConsoleColors.RESET);
				sc.nextLine();
			}
		    
        }while(m);

	}

		
	public boolean innerExecuteOperation(int operation, String nickname) {		
		String holder = null, coordinates = "";
		int row=0, col=0, pipeType=0;
		boolean n = true, m = true;
		switch(operation) {
			case 1:
				System.out.println("\n\n\n" + controller.printGrid());
				while (n){
					System.out.println("\n\n¿En que posicion desea ponerla? (ingrese las coordenadas con el formato 'x,y')\n");
					coordinates = sc.next();
					try{
						row = Integer.parseInt(coordinates.charAt(0) + "");
						col = Integer.parseInt(coordinates.charAt(2) + "");
						n = false;
					}
					catch (NumberFormatException e){
						System.out.println(ConsoleColors.RED+"\nCoordenadas invalidas, intente de nuevo.\n"+ConsoleColors.RESET);
					}
				}

				n = true;

				while(n){
					System.out.println("\n\n¿Que tipo de tuberia desea colocar?\n1)=\n2)| |\n3)O\n4)X\n");
					try{
						pipeType = sc.nextInt();
						pipeType+=2;
						if(pipeType<3||pipeType>6) System.out.println(ConsoleColors.RED+"\nTipo de tuberia invalido, intente de nuevo.\n"+ConsoleColors.RESET);
						else n = false;
					}
					catch (NumberFormatException e){
						System.out.println(ConsoleColors.RED+"\nTipo de tuberia invalido, Intehte de nuevo.\n"+ConsoleColors.RESET);
					}
				}

				if(!controller.play(row, col, pipeType)) System.out.println(ConsoleColors.RED+"\nInstruccion invalida\n"+ConsoleColors.RESET);

				System.out.println("\n\n\n" + controller.printGrid());

				break;
			case 2:
				holder = controller.simulate();

				if(holder!=null){
					System.out.println("\n\n\n"+holder);
					m = false;
				}
				else System.out.println(ConsoleColors.YELLOW+"\nSolucion incompleta\n"+ConsoleColors.RESET);
				break;
			case 3:
				System.out.println(ConsoleColors.CYAN+"\nRegresando al menu\n"+ConsoleColors.RESET);
				m = false;
				break;
			default:
				System.out.println(ConsoleColors.RED+"\nError, opción no válida\n"+ConsoleColors.RESET);
			
		}
		return m;
	}

}