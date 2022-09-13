package ui;

import model.Controller;
import java.util.Scanner;


public class PipeMania {

	private Controller controller = new Controller();

	private Scanner sc = new Scanner(System.in);

	

	public PipeMania(){}

	public static void main(String[] args) {

		PipeMania main = new PipeMania();
		
		System.out.println("Inicializando....");

		main.menu();

	}

	public void menu() {

		int option;

		do{System.out.println(
				"Seleccione una opcion\n" +
				"(1) Nueva partida\n" +
				"(2) Ver puntaje\n" +
                "(3) Para Salir"
				);
		    option= sc.nextInt();
		    sc.nextLine();
            executeOperation(option);
        }while(option!=3);
	}

	public void executeOperation(int operation) {
		
		
		
		switch(operation) {
		case 1:

			System.out.println("Ingrese su nickname");

			String nickname = sc.next();
            
			controller.initialize(nickname);
			
			System.out.println(controller.printGrid());
			
			innerMenu(nickname);

			break;
		case 2:
            System.out.println(controller.printScores());
			break;
        case 3:
            System.out.println("Bye!:D");
            break;
		default:
			System.out.println("Error, opción no válida");
			break;

		}
	}

	public void innerMenu(String nickname){

		int option;

		do{System.out.println(
				"Seleccione una opcion\n" +
				"(1) Poner tuberia\n" +
				"(2) Simular\n" +
                "(3) Para Salir"
				);
		    option= sc.nextInt();
		    sc.nextLine();
            innerExecuteOperation(option, nickname);
        }while(option!=3);

	}

		
	public void innerExecuteOperation(int operation, String nickname) {		
		String holder = null, coordinates = "";
		int row=0, col=0, pipeType=0;
		boolean n = true;
		switch(operation) {
			case 1:
				System.out.println("\n" + controller.printGrid());
				while (n){
					System.out.println("¿En que posicion desea ponerla? (ingrese las coordenadas con el formato 'x,y')");
					coordinates = sc.next();
					try{
						row = Integer.parseInt(coordinates.charAt(0) + "");
						col = Integer.parseInt(coordinates.charAt(2) + "");
						n = false;
					}
					catch (NumberFormatException e){
						System.out.println("Coordenadas invalidas, intente de nuevo.");
					}
				}

				n = true;

				while(n){
					System.out.println("¿Que tipo de tuberia desea colocar?\n1)=\n2)| |\n3)O");
					try{
						pipeType = sc.nextInt();
						pipeType+=2;
						if(pipeType<3||pipeType>5) System.out.println("Tipo de tuberia invalido, intente de nuevo.");
						else n = false;
					}
					catch (NumberFormatException e){
						System.out.println("Tipo de tuberia invalido, Intehte de nuevo.");
					}
				}

				if(!controller.play(row, col, pipeType)) System.out.println("Instrucciomn invalida");

				break;
			case 2:

				if(controller.simulate()!=null){
					System.out.println(controller.simulate()+"\n\n\n"+controller.finalScore(controller.addToLeaderBoard(nickname, controller.calculateScore())));
				}
				else System.out.println("Solucion incompleta");
				break;
			case 3:
				System.out.println("Regresando al menu");
				break;
			default:
				System.out.println("Error, opción no válida");
			
		}

	}

	public void game() {
		
		String nickName;

		nickName = sc.next();

	}

	public void viewScore() {
		// TODO - implement PipeMania.viewScore
		throw new UnsupportedOperationException();
	}

}