(def gr96opt [1 29 2 3 4 5 6 7 8 9 10 12 13 14 15 16 17 20 18 19 21 25 24 23 22 26 28 27 65 96 94 95 93 92 77 76 68 67 66 64 63 62 61 60 59 71 72 73 75 74 84 86 85 78 88 87 89 90 91 83 82 81 80 79 70 69 57 56 58 54 48 47 46 50 52 53 55 51 49 43 42 41 40 39 44 45 11 33 34 35 38 37 36 32 31 30])

(def gr96opt (mapv #(dec %) gr96opt))

(def gr96opt (conj gr96opt 0))