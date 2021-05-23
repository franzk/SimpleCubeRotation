package net.franzka.simplecuberotation

import kotlin.math.cos
import kotlin.math.sin

/***********************************
 * Cube
 * définit les sommets du cube et contient les méthodes de transformation du cube
 */
class Cube {

    companion object {
        private const val TAG = "Cube"
    }

    val sommets = mutableListOf<Sommet>()
    var angleX = 0F
    var angleY = 0F
    var angleZ = 0F
    var arrete = 0F

    fun createCube(_arrete: Float) {

        arrete = _arrete

        // pour fait en sorte que le barycentre du cube soit l'origine du repère x,y,z = 0,0,0
        // on démarre à moins la moitié de l'arrête
        val depart = -arrete / 2

        // définition des 8 sommets du cube
        sommets.add(Sommet(depart, depart, depart))
        sommets.add(Sommet(depart, (depart + arrete), depart))
        sommets.add(Sommet((depart + arrete), (depart + arrete), depart))
        sommets.add(Sommet((depart + arrete), depart, depart))
        sommets.add(Sommet(depart, depart, (depart + arrete)))
        sommets.add(Sommet(depart, (depart + arrete), (depart + arrete)))
        sommets.add(Sommet((depart + arrete), (depart + arrete), (depart + arrete)))
        sommets.add(Sommet((depart + arrete), depart, (depart + arrete)))
    }

    fun rotation() { // rotation du cube
        for (i in sommets.indices) {
            sommets[i] = rotation(sommets[i])
        }
    }

    private fun rotation(sommet: Sommet) : Sommet { // application de la matrice de rotation à un sommet

        // précalcul sinus et cosinus des angles de rotation
        val sinAngleX = sin(angleX.toDouble())
        val cosAngleX = cos(angleX.toDouble())
        val sinAngleY = sin(angleY.toDouble())
        val cosAngleY = cos(angleY.toDouble())
        val sinAngleZ = sin(angleZ.toDouble())
        val cosAngleZ = cos(angleZ.toDouble())

        // matrices de rotation
        with(sommet) {

            // matrices de rotation
            val newX =
                (cosAngleY*cosAngleZ) * x +
                ((-cosAngleX*sinAngleZ) + (sinAngleX*sinAngleY*cosAngleZ)) * y +
                ((sinAngleX*sinAngleZ) + (cosAngleX*sinAngleY*cosAngleZ)) * z

            val newY =
                (cosAngleY*sinAngleZ) * x +
                ((cosAngleX*cosAngleZ) + (sinAngleX*sinAngleY*sinAngleZ)) * y +
                ((-sinAngleX*cosAngleZ) + (cosAngleX*sinAngleY*sinAngleZ)) * z

            val newZ =
                (-sinAngleY) * x +
                (sinAngleX*cosAngleY) * y +
                (cosAngleX*cosAngleY) * z


            // on retourne les nouvelles coordonnées
            return Sommet(newX.toFloat(), newY.toFloat(), newZ.toFloat())
        }
    }

    class Sommet(var x: Float, var y: Float, var z: Float)

}
