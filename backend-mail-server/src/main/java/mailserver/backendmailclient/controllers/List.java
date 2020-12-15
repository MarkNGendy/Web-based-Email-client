package mailserver.backendmailclient.controllers;

public enum List {
    INBOX("INBOX"),SENT("SENT"),DRAFT("DRFAT"),TRASH("TRASH");

    public final String name;

    List(String name) {this.name = name;}
}
