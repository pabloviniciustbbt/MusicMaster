package com.pabloleal.musicmaster.models;

public enum GeneroMusical {
    AXE(1),
    BLUES(2),
    CLASSICA(3),
    ELETRONICA(4),
    FORRO(5),
    FUNK(6),
    GOSPEL(7),
    HIPHOP(8),
    INDIE(9),
    MPB(10),
    PAGODE(11),
    POP(12),
    PUNK(13),
    REGGAE(14),
    ROCK(15),
    SAMBA(16),
    SERTANEJO(17),
    SOUNDTRACKS(18),
    TRAP(19);

    private int numeroGenero;

    GeneroMusical(int numeroGenero) {
        this.numeroGenero = numeroGenero;
    }

    public int getNumeroGenero() {
        return numeroGenero;
    }

    public static GeneroMusical fromInt(int i){
        for (GeneroMusical generoMusical : GeneroMusical.values()){
            if (generoMusical.getNumeroGenero() == i){
                return generoMusical;
            }
        }

        throw new IllegalArgumentException("Nenhum categoria encontrada para a string fornecida: " + i);
    }
}
