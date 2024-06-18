import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Chatbot {

    PerguntasERespostas perguntas = new PerguntasERespostas();

    public void startChat() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem-vindo ao Chatbot!");
        boolean chatting = true;

        do {
            System.out.println("digite o seu nome por favor: ");
            perguntas.Nome = sc.nextLine();
        } while (perguntas.Nome.isBlank());

        System.out.println("Deseja ajuda? Sim(s) - Não(n)");
        String input = sc.nextLine();
        switch (input.toLowerCase()) {
            case "s":
                input = "ajuda";
                String ajuda = getResponse(input);
                System.out.println("Chatbot: " + ajuda);
                break;
            case "n":
                System.out.println("Seguiremos para as perguntas! Caso deseje ajuda digite (ajuda)");
                break;
            default:
                System.out.println("Seguiremos para as perguntas! Caso deseje ajuda digite (ajuda)");
                break;
        }

        while (chatting) {
            System.out.print("Me faça uma pergunta: ");
            input = sc.nextLine();
            String response = getResponse(input);
            System.out.println("Chatbot: " + response);
            if (input.equalsIgnoreCase("tchau") || input.equalsIgnoreCase("sair")) {
                chatting = false;
            }
        }
        sc.close();
    }

    private String getResponse(String input) {
        input = input.toLowerCase();
        if (input.contains("olá") || input.contains("oi")) {
            return getRandomResponse(perguntas.saudacoes) + " " + perguntas.Nome + "!";
        } else if (input.contains("tchau") || input.contains("sair")) {
            return getRandomResponse(perguntas.despedidas);
        } else if (input.contains("tempo") || input.contains("clima")) {
            return getRandomResponse(perguntas.temp);
        } else if (input.contains("data") || input.contains("dia") || input.contains("hora")) {
            return getCurrentDateTime();
        } else if (input.contains("ajuda") || input.contains("como está o dia hoje")) {
            return "Posso ajudar? segue as perguntas:\n" +
                    "|qual seu nome?\n" + //
                    "|qual e o proposito deste projeto?\n" + //
                    "|quais sao os participantes do grupo?\n" + //
                    "|conte uma piada?\n" + //
                    "|qual o melhor time do Brasil?\n" + //
                    "|qual é o papel do github neste projeto?\n" + //
                    "|quantos anos voce tem?\n" + //
                    "|faz um beatbox?\n" + //
                    "|quanto voce vale?";
        } else {
            return getFaqResponse(input);
        }
    }

    private String getRandomResponse(String[] responses) {
        int index = (int) (Math.random() * responses.length);
        return responses[index];
    }

    private String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(formatter);
    }

    private String getFaqResponse(String input) {
        for (String[] faq : perguntas.faqs) {
            if (input.contains(faq[0].toLowerCase())) {
                return faq[1];
            }
        }
        return "Desculpe, não entendi sua pergunta. Pode reformular?";
    }
}