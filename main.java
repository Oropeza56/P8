import java.util.HashMap;
import java.util.Scanner;


class Producto{

	String nombre;
	float precio;
	int cantDisp;
	int id_prod;

	boolean verificaDisponibilidad(int cantReq){
		if(cantReq<=cantDisp)
			return true;
		else
			return false;
	}

	void incrementarStock(int cant){
		cantDisp+=cant;
	}

	void decrementarStock(int cant){
		if(verificaDisponibilidad(cant))
			cantDisp-=cant;
		else
			System.out.println("Stock insuficiente");
	}

	public String toString(){
		return "Producto: "+nombre+"\nPrecio: "+precio;
	}
}

class Papeleria extends Producto{
	String tamanio;
	int cantHojas;
	String color;

	void defTamanoPapel(int alto, int ancho){
		if(alto==28 && ancho==22)
			tamanio="Carta";
		else if(alto==34 && ancho==22)
			tamanio="Oficio";
		else if(alto==28 && ancho==44)
			tamanio="Doble Carta";
		else
			tamanio="Pliego";
	}
}

class DeEscritura extends Producto{
	String tipopunta;
	String material;
	String formapunta;
}

class HerramientasComputo extends Producto{

}

class Cuaderno extends Papeleria{
	String tipoguia;

	Cuaderno(String color, int cantHojas, int alto, int ancho){
		nombre = "Cuaderno";
		precio = 80f;
		this.color = color;
		this.cantHojas = cantHojas;
		defTamanoPapel(alto, ancho);
	}
}

class PaqueteHojas extends Papeleria{
	PaqueteHojas(String color, int cantHojas, int alto, int ancho){
		nombre = "PaqueteHojas";
		precio = 500f;
		this.color = color;
		this.cantHojas = cantHojas;
		defTamanoPapel(alto, ancho);
	}
}

class Pluma extends DeEscritura{
	String color;

	Pluma(String color, String tipoPunta){
		this.color = color;
		tipopunta = tipoPunta;
		nombre = "Pluma";
		precio = 10f;
	}
}

class Lapiz extends DeEscritura{
	String color;

	Lapiz(String color, String tipoPunta){
		this.color = color;
		tipopunta = tipoPunta;
		nombre = "Lapiz";
		precio = 15f;
	}
}

class Marcador extends DeEscritura{
	String uso;
	String color;

	Marcador(String uso, String color){
		this.uso = uso;
		this.color = color;
		nombre = "Marcador";
		precio = 20f;
	}
}

class Calculadora extends HerramientasComputo{
	String marca;

	Calculadora(String marca){
		this.marca = marca;
		precio = 500f;
		nombre = "Calculadora";
	}
}

class Computadora extends HerramientasComputo{
	String SO;
	String color;

	Computadora(String SO, String color){
		this.SO = SO;
		this.color = color;
		precio = 3600f;
		nombre = "Computadora";
	}
}

class Cliente{
	String nombre;
	String email;
	String direccion;
	String formaPago;
	String celular;

	void actualizarDatos(String direccion, String celular){
		this.direccion=direccion;
		this.celular=celular;
	}

	Cliente(String n,String email){
		nombre=n;
		this.email=email;
	}
}

class Proveedor{
	String nombre;
	String RFC;
	String email;
	String direccion;
	String celular;

	Proveedor(String n, String rfc){
		nombre=n;
		this.RFC=rfc;
	}
}


class CarritoCompras{

	HashMap<String, Float> utiles = new HashMap <String, Float> ();	
	Cliente cliente;
	float costoTotal;
	//Producto articulos[];
	//int cont;

	CarritoCompras(Cliente c){
		cliente=c;
		//articulos=new Producto[10];
	}

	void agregarACarrito(Producto p){
		if(utiles.size() < 10){
			if(p.verificaDisponibilidad(1)){
				/*articulos[cont]=p;
				cont++;*/
				utiles.put(p.nombre , p.precio);

				p.decrementarStock(1);
			}	
		}else{
			System.out.println("Espacio insuficiente en carrito");
		}
	}

	void quitardelCarrito(String p){

		for(String i : utiles.keySet()){
			if(i == p){
				utiles.remove(i);
				recorreCarrito(i);
				//cont--;
			}
		}
	}

	void recorreCarrito(String index){
		for(String i : utiles.keySet()){
				//articulos[i]=articulos[i+1];
			System.out.println (utiles.get(i));
		}
	}

	void calcularCostoCarrito(){
		costoTotal=0;
		for(String i : utiles.keySet()){
			costoTotal = utiles.get(i) + costoTotal;
		}
	}

	void mostrarCarrito(){

		System.out.println("Carrito de "+cliente.nombre+":\n");

		for(String i : utiles.keySet()){
			System.out.println("Product."+ i + "$" + utiles.get(i) );
		}
	}

	public String toString(){
		return "Carrito de "+cliente.nombre+":\n"+super.toString();
	}
}

class TiendaOnline{

	static void agregarACarrito(CarritoCompras carro, Scanner sc){
		System.out.println ("\n¿De que categoria desea comprar? ");
		System.out.println ("Empezamos con sus compras\n" + "En la tienda hay:\n(M) Materiales de escritura -> Plumas, lapices, plumones,etc.\n(H) Herramientas de Computo -> Calculadoras, computadoras, etc\n(P) Papeleria -> Cuadernos, paquetes de hojas");
				
		String opcion = sc.next();
		switch (opcion) {

			case "M" :
				System.out.println("DE ESCRITURA\n Lapices (a)  Pluma (b)   Marcadores (c) ");
				String op1 = sc.next();
				switch (op1){
					case "a" :
						System.out.println ("LAPICES. Indique el tipo de punta y color\n ");
						String tipoP = sc.next();
						String colorL = sc.next();

						Lapiz p1 = new Lapiz(colorL, tipoP);
						p1.incrementarStock (10);
						carro.agregarACarrito(p1);
						break;

					case "b" :
						System.out.println("PLUMAS. Indique tipo de punta y color\n");
						String tipoPL= sc.next();
						String colorPL= sc.next();

						Pluma p2 = new Pluma(colorPL, tipoPL);
						p2.incrementarStock(2);
						carro.agregarACarrito(p2);
						break;
						
					case "c":
						System.out.println("MARCADORES. Indique uso (decoracion o resaltado), punta y color");
						String usoM = sc.next();
						String colorM = sc.next();

						Marcador p3 = new Marcador(colorM, usoM);
						carro.agregarACarrito(p3);
						break;

					default:
						System.out.println ("OPCION INVALIDA");
				} break; 			
			case "H":
				System.out.println("Herramientas de Computo -> Calculadoras (C) , computadoras (O)");
				String op2 = sc.next();
				switch (op2){
					case "C" :
						System.out.println ("CALCULADORA. Indique la marca\n ");
						String marcaC = sc.next();
							
						Calculadora p4 = new Calculadora(marcaC);
						p4.incrementarStock (3);
						carro.agregarACarrito(p4);
						break;

					case "O" :
						System.out.println("COMPUTADORAS. Indique la marca y el color\n");
						String marcaCo= sc.next();
						String colorCo= sc.next();

						Computadora p5 = new Computadora(marcaCo, colorCo);
						p5.incrementarStock(6);
						carro.agregarACarrito(p5);
						break;

					default:
						System.out.println ("OPCION INVALIDA");

				} break;
			case "P":
				System.out.println ("Papeleria -> Cuadernos (U), paquetes de hojas (H)");
				String op3 = sc.next();
				switch (op3){
					case "U" :
						System.out.println ("CUADERNOS. Indique color, cantidad y tamano (alto y ancho) \n ");
						String colorCu = sc.next();
						int cantidadH = sc.nextInt();
						int anchoLC = sc.nextInt();
						int altoLC = sc.nextInt();
													
						Cuaderno p6 = new Cuaderno(colorCu, cantidadH, altoLC, anchoLC);
						p6.incrementarStock (7);
						carro.agregarACarrito(p6);
						break;

					case "H" :
						System.out.println("PAQUETE DE HOJAS.  Indique color, cantidad y tamano (alto y ancho)\n");
						String colorH= sc.next();
						int cantHojas= sc.nextInt();
						int ancho = sc.nextInt();
						int alto = sc.nextInt();
							
						PaqueteHojas p7 = new PaqueteHojas(colorH, cantHojas, alto, ancho);
						p7.incrementarStock(7);
						carro.agregarACarrito(p7);
						break;

					default:
						System.out.println ("OPCION INVALIDA");
				}
			default:
				System.out.println ("OPCION INVALIDA");
		}
	}
	public static void main(String[] a){

		Scanner sc= new Scanner(System.in);
		System.out.println ("Buen dia ingrese su nombre y email, por favor");
		
		String nombreU = sc.next();
		String email = sc.next();

		Cliente c = new Cliente( nombreU, email);
		CarritoCompras carro = new CarritoCompras(c);
		int cas;

		do{
			System.out.println("\nELIGE DEL MENU: \n1. Agregar al carrito\n2.Quitar del carrito\n3.Mostrar el carrito\n4.Calcular costo total\n5.Salir ");
			cas = sc.nextInt();
			switch(cas){
				case 1:
					agregarACarrito(carro, sc);
					continue;		
				case 2:
					System.out.println ("¿QUE DESEA QUITAR DEL CARRITO?\n");
					carro.mostrarCarrito();
					System.out.print("--> ");		
					String nombreObj = sc.next();
					carro.quitardelCarrito(nombreObj);	
					continue;
				case 3:
					System.out.println ("ESTOS SON SUS PRODUCTOS\n");
					carro.mostrarCarrito();
					continue;
				case 4:
					carro.calcularCostoCarrito();
					System.out.println("EL TOTAL DE SU COMPRA ES:" + carro.costoTotal);
					continue;
				
				default:
					System.out.println("Opcion invalida.");
			}

		} while(cas != 5);
	}

}
	




