package ui;

import model.Controller;
import java.util.Scanner;
import java.time.*;

public class PipeMania {

	private Controller controller = new Controller();

	private Scanner sc = new Scanner(System.in);

	private clock = Clock.systemDefaultZone();

	public PipeMania(){}

	public static void main(String[] args) {

		PipeMania main = new PipeMania
		
		System.out.println("Inicializando....")

		main.menu();

	}

	public String menu() {

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
		
		Instant start=null;
		Instant end = null;
		
		switch(operation) {
		case 1:

			System.out.println("Ingrese su nickname");

			String nickname = sc.next();
            
			controller.initialize(nickname);
			
			System.out.println(controller.printGrid());
			
			controller.innerExecuteOperation();
			
			start = clock.instant();

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
			return start;

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
            executeOperation(option);
        }while(option!=3);

	}

	public Instant innerExecuteOperation(int operation) {
		
		Instant end=null;
		
		switch(operation) {
		case 1:
            
			break;
		case 2:
            if(simulate){

				end=clock.instant();

			}
			break;
        case 3:
            System.out.println("Regresando al menu");
            break;
		default:
			System.out.println("Error, opción no válida");
		
		}

		return end;

	}

	public void game() {
		
		String nickName;

		nickName = sc.next();

	}

	public void viewScore() {
		// TODO - implement PipeMania.viewScore
		throw new UnsupportedOperationException();
	}

	public boolean simulate(String nickName) {
		



	}

}