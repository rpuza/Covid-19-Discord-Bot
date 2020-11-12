import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Quarantine {
    ArrayList<String> activities = new ArrayList<>(Arrays.asList("Among_Us", "Scener", "Virtual_Party", "FaceTime", "Uno_Online", "Spoon", "Splendee"));
    ArrayList<String> activitiesNum = new ArrayList<>(Arrays.asList("4-10", "2+", "2+", "2+", "4", "2+", "2-4"));
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(DetectOnOff.filterOn){
            String[] LIST_OF_WORDS = {"meet", "restaurant", "theater", "gym", "concert", "stadium", "bar", "buffet", "visit", "mall", "sex", "party"};
            String[] message = e.getMessage().getContentRaw().split(" ");
            for(int i = 0;i < message.length;i++){
                boolean match = false;
                //Check each message for each words in the list
                for(int b = 0; b < LIST_OF_WORDS.length;b++){
                    if (message[i].equalsIgnoreCase(LIST_OF_WORDS[b])){
                        e.getMessage().delete().queue();
                        match = true;
                        if(DetectMessage.allowed){ //Prints a message IF enabled by ~detectfakenews
                            e.getChannel().sendMessage("Avoid in-person gatherings if possible! Coronavirus is still severe in our country. Type '~cases [location]' to find out the number of confirmed cases and deaths in your area." + e.getMember().getUser().getName()).queue();
                            int rand = (int) (Math.random() % activities.size());
                            e.getChannel().sendMessage(String.format("How about playing %s with your family or friends?! It's safe, fun, and very interactive! All you need is a stable Internet connection and gatherings of %s people.\n",activities.get(rand), activitiesNum.get(rand))).queue(); //Alternative activities suggestion
                        }
                    }
                }
                System.out.println(message[i] + " " + match); //Just a report for console
            }
        }
    }
}
