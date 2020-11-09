import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class main extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, FileNotFoundException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NzUzNjk5NjUzODE1NTAwOTIw.X1p_gA.pRobNCcl11A_kPjrOvivZnCy3sg";
        builder.addEventListener(new Detect());
        builder.addEventListener(new DetectMessage());
        builder.addEventListener(new DetectOnOff());
        builder.addEventListener(new HandWashReminder());
        builder.addEventListener(new Quarantine());
//        System.out.println(selectActivity());
        /*builder.setToken(token);
        builder.addEventListener(new main());
        builder.buildAsync();*/
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("We received a message from " +
                event.getAuthor().getName() + ": " +
                event.getMessage().getContentDisplay());
        if(event.getMessage().getContentRaw().equals("Say Hello!")){
            event.getChannel().sendMessage("Hello World!").queue();
        }
    }
    public static String selectActivity() throws FileNotFoundException {
        File file = new File("C:\\Users\\puza7\\IdeaProjects\\covid19DiscordBot\\Assets\\Activities");
        Scanner scan = new Scanner(file);
        int lineNumber = 1;
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            System.out.println("line " + lineNumber + " :" + line);
            lineNumber++;
        }
        // Find # of users?!
        ArrayList<String> activities = new ArrayList<>(Arrays.asList("Among_Us", "Scener", "Virtual_Party", "FaceTime", "Uno_Online", "Spoon", "Splendee"));
        ArrayList<String> activitiesNum = new ArrayList<>(Arrays.asList("4-10", "2+", "2+", "2+", "4", "2+", "2-4"));
        int rand = (int) (Math.random() % activities.size());
        System.out.printf("How about playing %s with your family or friends?! It's safe, fun, and very interactive! All you need is a stable Internet connection and gatherings of %s people.\n",activities.get(rand), activitiesNum.get(rand));
        return "lol";
    }
}
