package it.unicampania.lsadm.mybeers.datamodel

/**
 * Singleton per simulare il funzionamento di un ipotetico Database
 */
object DataBase {

    // Array per simulare il contenuto del database
    private var birre = ArrayList<Birra>()

    // Inizializzatore per popolare il database con qualche dato
    init {
        birre.add(Birra("Blonde", "Leffe", "mista", 6.6f))
        birre.add(Birra("La Biere du Demon", "Brasserie Goudale", "lager", 12f))
        birre.add(Birra("BrewMaster's Choice IPA", "Target 2000", "ipa", 5.8f))
    }

    // Restituisce l'elenco di tutte le birre presenti
    fun getElencoBirre(): ArrayList<Birra> {
        return birre
    }

    /**
     * Aggiunge una nuova birra nel database
     */
    fun salvaBirra(birra: Birra) {
        birre.add(birra)
    }
    fun eliminaBirra(birra: Birra){

        birre.remove(birra)

    }
}