package com.fidelidad.modelo;


public enum Nivel {
    BRONCE {
        @Override
        public double getMultiplicador() {
            return 1.0;
        }
    },
    PLATA {
        @Override
        public double getMultiplicador() {
            return 1.1;
        }
    },
    ORO {
        @Override
        public double getMultiplicador() {
            return 1.2;
        }
    },
    PLATINO {
        @Override
        public double getMultiplicador() {
            return 1.3;
        }
    };

    public abstract double getMultiplicador();
}
