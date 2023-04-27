package ui;
 import java.util.Scanner;


public class GreenApp { 
    private static Scanner scanner = new Scanner(System.in);
    private static Gerente gerente = new Gerente();

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("******** GreenApp ********");
            System.out.println("1. Register project");
            System.out.println("2. Manage project stages");
            System.out.println("3. Register knowledge capsule");
            System.out.println("4. Approve capsule");
            System.out.println("5. Publish capsule");
            System.out.println("6. View knowledge capsules");
            System.out.println("7. Generate reports");
            System.out.println("0. Exit");
            System.out.print("Enter the desired option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0:
                    exit = true;
                    break;
                case 1:
                    registerProject();
                    break;
                case 2:
                    manageProjectStages();
                    break;
                case 3:
                    finishProjectStage();
                    break;
                case 4:
                    registerKnowledgeCapsule();
                    break;
                case 5:
                    approveCapsule();
                    break;
                case 6:
                    publishCapsule();
                    break;
                case 7:
                    viewKnowledgeCapsules();
                    break;
                case 8:
                    generateReports();
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    break;
            }
        }
    }

    //1.) Implementation of the "Register project" option
    private static void registerProject() {
        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();
        System.out.print("Enter project description: ");
        String projectDescription = scanner.nextLine();
        System.out.print("Enter project manager name: ");
        String managerName = scanner.nextLine();

        // Create Gerente object
        Gerente projectManager = new Gerente(managerName);

        // Create Proyecto object
        Proyecto project = new Proyecto(projectName, projectDescription, projectManager);

        // Add project to the manager's list of projects
        projectManager.addProject(project);

        System.out.println("Project registered successfully.");
    }

    //2.) Implementation of the "Manage project stages" option
    private static void manageProjectStages() {
        System.out.print("Enter project name: ");
        String projectName = scanner.nextLine();
        Proyecto project = gerente.findProject(projectName);
        if (project != null) {
            System.out.println("1. Add stage");
            System.out.println("2. Modify stage");
            System.out.println("3. Remove stage");
            System.out.print("Enter the desired option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.print("Enter stage name: ");
                    String stageName = scanner.nextLine();
                    project.addStage(stageName);
                    break;
                case 2:
                    System.out.print("Enter stage name to modify: ");
                    String stageToModify = scanner.nextLine();
                    System.out.print("Enter the new stage name: ");
                    String newStageName = scanner.nextLine();
                    project.modifyStage(stageToModify, newStageName);
                    break;
                case 3:
                    System.out.print("Enter stage name to remove: ");
                    String stageToRemove = scanner.nextLine();
                    project.removeStage(stageToRemove);
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    break;
            }
        } else {
            System.out.println("Project not found.");
        }
    }
    


    //3.) Implementation of the "Finish a project stage" option
        private static void finishProjectStage() {
        System.out.print("Enter the project name: ");
        String projectName = scanner.nextLine();
        Project project = manager.searchProject(projectName);
        if (project != null) {
        System.out.print("Enter the stage name: ");
        String stageName = scanner.nextLine();
        Stage stage = project.searchStage(stageName);
        if (stage != null) {
        System.out.print("Was the stage approved? (y/n): ");
        String approvedInput = scanner.nextLine();
        boolean approved = approvedInput.equalsIgnoreCase("y");
        System.out.print("Enter the completion date of the stage (dd/mm/yyyy): ");
        String actualCompletionDateInput = scanner.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar actualCompletionDate = Calendar.getInstance();
        try {
        actualCompletionDate.setTime(formatter.parse(actualCompletionDateInput));
        } catch (ParseException e) {
        System.out.println("The date entered is invalid.");
        return;
        }
        manager.finishProjectStage(project, stage, approved, actualCompletionDate);
        System.out.println("The stage has been successfully completed.");
        } else {
        System.out.println("Stage not found.");
        }
        } else {
        System.out.println("Project not found.");
     }
    }

    //4.) Implementation of the "Register knowledge capsule" option
    private static void registerKnowledgeCapsule() {
        System.out.print("Enter the name of the project: ");
        String projectName = scanner.nextLine();
        Project project = manager.searchProject(projectName);
        if (project == null) {
            System.out.println("The project does not exist.");
            return;
        }
        System.out.print("Enter the name of the knowledge capsule: ");
        String capsuleName = scanner.nextLine();
        System.out.print("Enter the description of the knowledge capsule: ");
        String capsuleDescription = scanner.nextLine();
        
        Capsule capsule = new Capsule(capsuleName, capsuleDescription, project);
        project.addCapsule(capsule);
        System.out.println("Knowledge capsule registered successfully in the " + project.getName() + " project.");
    }

    //5.) Implementation of the "Capsule Approval" option
    private static void approveCapsule() {
        System.out.print("Enter the project name: ");
        String projectName = scanner.nextLine();
        System.out.print("Enter the ID of the capsule to approve: ");
        int capsuleID = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        Project project = manager.searchProject(projectName);
        if (project == null) {
            System.out.println("The project does not exist");
            return;
        }

        Capsule capsule = project.searchCapsule(capsuleID);
        if (capsule == null) {
            System.out.println("The capsule does not exist");
            return;
        }

        if (capsule.isApproved()) {
            System.out.println("The capsule has already been approved");
            return;
        }

        capsule.approve();
        System.out.println("The capsule has been approved successfully");
    }
    

    //6.) Implementation of the "Publication of Capsules" option
    private static void publishCapsule() {
        System.out.print("Enter the name of the project: ");
        String projectName = scanner.nextLine();
        Project project = manager.findProject(projectName);
        if (project == null) {
            System.out.println("The project does not exist.");
            return;
        }

        System.out.print("Enter the name of the knowledge capsule: ");
        String capsuleName = scanner.nextLine();
        KnowledgeCapsule capsule = project.findCapsule(capsuleName);

        if (capsule == null) {
            System.out.println("The knowledge capsule does not exist.");
            return;
        }

        if (capsule.isPublished()) {
            System.out.println("The knowledge capsule has already been published.");
            return;
        }

        capsule.publish();
        System.out.println("The knowledge capsule has been published.");
    }

    //7.) Implementation of the "Knowledge Capsules Query" option
    private static void queryKnowledgeCapsules() {
    System.out.println("Select a query option:");
    System.out.println("1. Query all knowledge capsules.");
    System.out.println("2. Query knowledge capsules by project.");
    System.out.println("3. Query knowledge capsules by collaborator.");
    int queryOption = scanner.nextInt();
    scanner.nextLine(); 
    switch (queryOption) {
        case 1:
            List<Capsule> capsules = manager.getCapsules();
            for (Capsule capsule : capsules) {
                System.out.println(capsule);
            }
            break;
        case 2:
            System.out.print("Enter the name of the project: ");
            String projectName = scanner.nextLine();
            List<Capsule> projectCapsules = manager.getCapsulesByProject(projectName);
            for (Capsule capsule : projectCapsules) {
                System.out.println(capsule);
            }
            break;
        case 3:
            System.out.print("Enter the collaborator's email address: ");
            String collaboratorEmail = scanner.nextLine();
            List<Capsule> collaboratorCapsules = manager.getCapsulesByCollaborator(collaboratorEmail);
            for (Capsule capsule : collaboratorCapsules) {
                System.out.println(capsule);
            }
            break;
        default:
            System.out.println("Invalid option. Please try again.");
            break;
    }
}
    //8.) Implementation of the "Register Project" option
        private static void registerProject() {
        System.out.print("Enter the name of the project: ");
        String projectName = scanner.nextLine();
        System.out.print("Enter the project description: ");
        String projectDescription = scanner.nextLine();
        System.out.print("Enter the name of the project manager: ");
        String managerName = scanner.nextLine();
        // Create an object of the Gerente class
        Gerente projectManager = new Gerente(managerName);

        // Create an object of the Proyecto class
        Proyecto project = new Proyecto(projectName, projectDescription, projectManager);
    
        // Add the project to the manager's project list
        projectManager.agregarProyecto(project);
    
        System.out.println("The project has been successfully registered.");
    }
    
}    


