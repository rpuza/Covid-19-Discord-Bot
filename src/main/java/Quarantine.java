import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Quarantine {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(DetectOnOff.filterOn){
            String[] LIST_OF_WORDS = {"meet", "restaurant", "theater", "gym", "concert", "stadium", "bar", "buffet", "visit", "mall", "sex", "party"};
            String[] message = e.getMessage().getContentRaw().split(" ");
            for(int i = 0;i < message.length;i++){
                boolean match = false;
                //Check each message for each myths
                for(int b = 0; b < LIST_OF_WORDS.length;b++){
                    if (message[i].equalsIgnoreCase(LIST_OF_WORDS[b])){
                        e.getMessage().delete().queue();
                        match = true;
                        if(DetectMessage.allowed){ //Prints a message IF enabled by ~detectfakenews
                            e.getChannel().sendMessage("Avoid in-person gatherings if possible! Coronavirus is still severe in our country. Type '~cases [location]' to find out the number of confirmed cases and deaths in your area." + e.getMember().getUser().getName()).queue();
                        }
                    }
                }
                System.out.println(message[i] + " " + match); //Just a report for console
            }
        }
    }
}
