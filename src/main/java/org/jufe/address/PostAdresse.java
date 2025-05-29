package org.jufe.address;

import jakarta.mail.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostAdresse extends Address {

    private String strasse;

    private String hausnummer;

    private String plz;

    private String stadt;

    public String getType() {
        // isses ditte? no real world use case found yet - they played us for absolute fools
        return PostAdresse.class.getSimpleName();
    }

    public String toString() {
        return String.format("%s %s | %s %s", strasse, hausnummer, plz, stadt);
    }

    public boolean equals(Object o) {
        if (o instanceof PostAdresse p) {
            return strasse.equals(p.strasse) && hausnummer.equals(p.hausnummer) && plz.equals(p.plz) && stadt.equals(p.stadt);
        }
        return false;
    }

    public int hashCode() {
        return this.strasse.hashCode() * 17 + this.hausnummer.hashCode() * 16 + this.plz.hashCode() * 15 + this.stadt.hashCode() * 14;

    }

}
