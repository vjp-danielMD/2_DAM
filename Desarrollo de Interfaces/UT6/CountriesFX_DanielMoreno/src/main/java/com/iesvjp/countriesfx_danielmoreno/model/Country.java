package com.iesvjp.countriesfx_danielmoreno.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Country {
    private Name name;
    private List<String> capital;
    private String region;
    private String subregion;
    private long population;
    private Map<String, Currency> currencies;
    private Map<String, String> languages;
    private Flags flags;
    private Map<String, Translation> translations;

    public static class Name { public String common; public String official; }
    public static class Currency { public String name; public String symbol; }
    public static class Flags { public String png; }
    public static class Translation { public String common; }

    // Obtener nombre en español si existe, si no, el común
    public String getNameEs() {
        if (translations != null && translations.containsKey("spa")) {
            return translations.get("spa").common;
        }
        return name != null ? name.common : "N/A";
    }

    public String getOfficialNameEs() {
        return (name != null) ? name.official : "N/A";
    }

    public String getCapital() {
        return (capital != null && !capital.isEmpty()) ? capital.get(0) : "N/A";
    }

    public String getRegion() { return region != null ? region : "N/A"; }
    public String getSubregion() { return subregion != null ? subregion : "N/A"; }
    public long getPopulation() { return population; }
    public String getFlagUrl() { return flags != null ? flags.png : ""; }

    public String getCurrenciesAsString() {
        if (currencies == null || currencies.isEmpty()) return "N/A";
        return currencies.values().stream()
                .map(c -> c.name + " (" + (c.symbol != null ? c.symbol : "") + ")")
                .collect(Collectors.joining(", "));
    }

    public String getLanguagesAsString() {
        if (languages == null || languages.isEmpty()) return "N/A";
        return String.join(", ", languages.values());
    }

    @Override
    public String toString() { return getNameEs(); }
}