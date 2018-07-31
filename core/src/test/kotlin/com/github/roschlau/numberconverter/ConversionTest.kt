package com.github.roschlau.numberconverter

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ConversionTest {

    @DisplayName("Roman to decimal")
    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource(
        "I,          1",
        "II,         2",
        "III,        3",
        "IIII,       4",
        "IV,         4",
        "V,          5",
        "VI,         6",
        "VII,        7",
        "VIII,       8",
        "IX,         9",
        "VIIII,      9",
        "X,          10",
        "XI,         11",
        "XX,         20",
        "XXII,       22",
        "XXX,        30",
        "XXXIII,     33",
        "XL,         40",
        "XXXX,       40",
        "XLIV,       44",
        "XXXXIIII,   44",
        "L,          50",
        "LV,         55",
        "LX,         60",
        "LXVI,       66",
        "LXX,        70",
        "LXXVII,     77",
        "LXXX,       80",
        "LXXXVIII,   88",
        "LXXXX,      90",
        "XC,         90",
        "LXXXXVIIII, 99",
        "XCIX,       99",
        "C,          100",
        "CXIX,       119",
        "CXVIIII,    119",
        "CC,         200",
        "CCXIIII,    214",
        "CCXIV,      214",
        "CCC,        300",
        "CCCIIII,    304",
        "CCCIV,      304",
        "CCCC,       400",
        "CD,         400",
        "CCCCLXVIII, 468",
        "CDLXVIII,   468",
        "D,          500",
        "DXLV,       545",
        "DXXXXV,     545",
        "DC,         600",
        "DCXXXIII,   633",
        "DCC,        700",
        "DCCLXXIIII, 774",
        "DCCLXXIV,   774",
        "DCCC,       800",
        "DCCCLXXXXV, 895",
        "DCCCXCV,    895",
        "CM,         900",
        "DCCCC,      900",
        "CMXXI,      921",
        "DCCCCXXI,   921",
        "M,          1000"
    )
    fun `roman to decimal conversion`(roman: String, expectedDecimal: String) {
        assertThat(convert(roman), equalTo(success(expectedDecimal)))
    }

    @DisplayName("Decimal to roman")
    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource(
        "1,    I",
        "2,    II",
        "3,    III",
        "4,    IIII",
        "5,    V",
        "6,    VI",
        "7,    VII",
        "8,    VIII",
        "9,    VIIII",
        "10,   X",
        "11,   XI",
        "20,   XX",
        "22,   XXII",
        "30,   XXX",
        "33,   XXXIII",
        "40,   XXXX",
        "44,   XXXXIIII",
        "50,   L",
        "55,   LV",
        "60,   LX",
        "66,   LXVI",
        "70,   LXX",
        "77,   LXXVII",
        "80,   LXXX",
        "88,   LXXXVIII",
        "90,   LXXXX",
        "99,   LXXXXVIIII",
        "100,  C",
        "119,  CXVIIII",
        "200,  CC",
        "214,  CCXIIII",
        "300,  CCC",
        "304,  CCCIIII",
        "400,  CCCC",
        "468,  CCCCLXVIII",
        "500,  D",
        "545,  DXXXXV",
        "600,  DC",
        "633,  DCXXXIII",
        "700,  DCC",
        "774,  DCCLXXIIII",
        "800,  DCCC",
        "895,  DCCCLXXXXV",
        "900,  DCCCC",
        "921,  DCCCCXXI",
        "1000, M"
    )
    fun `decimal to roman conversion`(roman: String, expectedDecimal: String) {
        assertThat(convert(roman), equalTo(success(expectedDecimal)))
    }

}
