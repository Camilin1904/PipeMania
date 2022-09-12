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
			
			innerMenu();

			break;
		case 2:
            System.out.println(controller.printScores());
			break;
        case 3:
            System.out.println("Bye!");
            break;
		default:
			System.out.println("Error, opción no válida");
			break;

		}
	}

	public void innerMenu(){

		int option;

		do{System.out.println(
				"Seleccione una opcion\n" +
				"(1) Poner tuberia\n" +
				"(2) Simular\n" +
                "(3) Para Salir"
				);
		    option= sc.nextInt();
		    sc.nextLine();
            innerExecuteOperation(option);
        }while(option!=3);

	}

	public void innerExecuteOperation(int operation) {
		
		
		
		switch(operation) {
		case 1:
            
			break;
		case 2:
            
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