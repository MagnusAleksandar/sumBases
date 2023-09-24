package Modelo;

public class Diccionario {
    public static int convert1024In(char input) {
        int fin = 0;
        switch (input) {
            case 'Ñ':
                fin = (int) 24;
                break;
            case 'ñ':
                fin = (int) 51;
                break;
            case '¥':
                fin = (int) 109;
                break;
            case 'µ':
                fin = (int) 116;
                break;
            case '·':
                fin = (int) 117;
                break;
            case 'É':
                fin = (int) 122;
                break;
            case 'Ö':
                fin = (int) 123;
                break;
            case '¿':
                fin = (int) 124;
                break;
            case '?':
                fin = (int) 125;
                break;
            case ' ':
                fin = (int) 130;
                break;
            case 'ÿ':
                fin = (int) 160;
                break;
            case 'Γ':
                fin = (int) 161;
                break;
            case 'Θ':
                fin = (int) 162;
                break;
            default:
                if (input >= 48 && input <= 57)
                    input -= 48;
                else if (input > 64 && input <= 78)
                    input -= 55;
                else if (input > 78 && input <= 90)
                    input -= 54;
                else if (input > 96 && input <= 110 || input >= 170 && input < 173)
                    input -= 60;
                else if (input > 110 && input <= 122)
                    input -= 59;
                else if (input >= 33 && input < 65)
                    input += 31;
                else if (input >= 91 && input < 97)
                    input += 5;
                else if (input >= 123 && input < 127)
                    input -= 21;
                else if (input >= 161 && input < 164)
                    input -= 55;
                else if (input >= 176 && input < 179)
                    input -= 63;
                else if (input >= 186 && input < 190)
                    input -= 68;
                else if (input >= 196 && input < 200)
                    input -= 70;
                else if (input >= 220 && input < 227)
                    input -= 89;
                else if (input >= 228 && input < 240)
                    input -= 90;
                else if (input >= 242 && input < 248)
                    input -= 92;
                else if (input >= 249 && input < 253)
                    input -= 93;
                fin = (int) input;
                break;
        }
        return fin;
    }

    public static char convertIn1024(int input) {
        char fin = 0;
        switch (input) {
            case 24:
                fin = 'Ñ';
                break;
            case 51:
                fin = 'ñ';
                break;
            case 109:
                fin = '¥';
                break;
            case 116:
                fin = 'µ';
                break;
            case 117:
                fin = '·';
                break;
            case 122:
                fin = 'É';
                break;
            case 123:
                fin = 'Ö';
                break;
            case 124:
                fin = '¿';
                break;
            case 125:
                fin = '?';
                break;
            case 130:
                fin = ' ';
                break;
            case 160:
                fin = 'ÿ';
                break;
            case 161:
                fin = 'Γ';
                break;
            case 162:
                fin = 'Θ';
                break;
            default:
                if (input <= 9 && input >= 0)
                    input += 48;
                else if (input > 9 && input <= 23)
                    input += 55;
                else if (input > 24 && input <= 36)
                    input += 54;
                else if (input > 36 && input <= 50 || input >= 110 && input < 113)
                    input += 60;
                else if (input > 51 && input < 64)
                    input += 59;
                else if (input >= 64 && input < 96)
                    input -= 31;
                else if (input >= 96 && input < 97)
                    input -= 5;
                else if (input >= 102 && input < 106)
                    input += 21;
                else if (input >= 106 && input < 109)
                    input += 55;
                else if (input >= 113 && input < 116)
                    input += 63;
                else if (input >= 118 && input < 122)
                    input += 68;
                else if (input >= 126 && input < 130)
                    input += 70;
                else if (input >= 131 && input < 138)
                    input += 89;
                else if (input >= 138 && input < 150)
                    input += 90;
                else if (input >= 150 && input < 156)
                    input += 92;
                else if (input >= 156 && input < 160)
                    input += 93;
                fin = (char) input;
                break;
        }
        return fin;
    }
}
