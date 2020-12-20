package mailserver.backendmailclient.classes;

import mailserver.backendmailclient.interfaces.ISearch;

import java.util.ArrayList;
import java.util.List;

public class Search implements ISearch {


    @Override
    public List<Mail> subjectSearch(List<Mail> list, String search) {
        List<Mail> result = new ArrayList<Mail>();
        for(Mail m : list){
            if(m.getSubject().contains(search)){
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public List<Mail> bodySearch(List<Mail> list, String search) {
        List<Mail> result = new ArrayList<Mail>();
        for(Mail m : list){
            if(m.getBody().contains(search)){
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public List<Mail> senderSearch(List<Mail> list, String search) {
        List<Mail> result = new ArrayList<Mail>();
        for(Mail m : list){
            if(m.getSender().contains(search)){
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public List<Mail> receiversSearch(List<Mail> list, String search) {
        List<Mail> result = new ArrayList<Mail>();
        for(Mail m : list){
            if(m.getReceiver().toString().contains(search)){
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public List<Mail> wholeSearch(List<Mail> list, String search) {
        List<Mail> result = new ArrayList<Mail>();
        for(Mail m : list){
            if(m.getSubject().contains(search)){
                result.add(m);
            }
            else if(m.getBody().contains(search)){
                result.add(m);
            }
            else if(m.getSender().contains(search)){
                result.add(m);
            }
            else if(m.getReceiver().toString().contains(search)){
                result.add(m);
            }
        }
        return result;
    }
}
