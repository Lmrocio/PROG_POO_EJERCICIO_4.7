class Cuenta(val numero: Int,
             var saldoDisponible: Double) {

    fun consultarSaldo():Double{
        return saldoDisponible
    }

    fun recibirAbono(cantidad: Double){
        saldoDisponible += cantidad

        println("Se han abonado $cantidad €, el saldo actualizado es de: $saldoDisponible €.")
    }

    fun realizarPago(cantidad: Double):Boolean{
        if(saldoDisponible >= cantidad){
            saldoDisponible -= cantidad
            return true
        } else{
            return false
        }
    }
}

class Persona(val dni: String){

    val cuentas = Array<Cuenta?>(3) {null}

    fun añadirCuenta(cuenta: Cuenta){
        for(i in cuentas.indices){
            if(cuentas[i] == null){
                cuentas[i] = cuenta
                return
            }
        }

        println("Máximo de cuentas registradas.")
    }

    fun moroso(): Boolean{
        for(cuenta in cuentas){
           if (cuenta != null && cuenta.consultarSaldo() < 0){
               return true
           }
        }
        return false
    }

    fun transferencia(personab: Persona, cantidad: Double, cuentaOrigen: Int, cuentaDestino: Int): Boolean{

        val cuenta1 = cuentas[cuentaOrigen]
        val cuenta2 = personab.cuentas[cuentaDestino]

        if(cuenta1 != null && cuenta2 != null && cuenta1.realizarPago(cantidad)){
            cuenta2.recibirAbono(cantidad)
            return true
        } else return false
    }
}

fun main(){

    val persona1 = Persona("40123123A")

    val cuenta1 = Cuenta( 1298343429, 0.0)
    val cuenta2 = Cuenta(5990434, 700.0)

    persona1.añadirCuenta(cuenta1)
    persona1.añadirCuenta(cuenta2)

    cuenta1.recibirAbono(1100.0)

    cuenta2.realizarPago(750.0)

    if(persona1.moroso()){
        println("La persona de dni ${persona1.dni} debe dinero.")

    } else println("La persona de dni ${persona1.dni} no debe dinero.")



    val transferencia = persona1.transferencia(persona1, 500.0, 0, 1)

    if (transferencia){
        println("La transferencia se realizó con exito")

    } else println("La transferencia no pudo realizarse..")
}
