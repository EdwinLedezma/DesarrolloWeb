<!-- Use this file to provide workspace-specific custom instructions to Copilot. For more details, visit https://code.visualstudio.com/docs/copilot/copilot-customization#_use-a-githubcopilotinstructionsmd-file -->
- [ ] Verify that the copilot-instructions.md file in the .github directory is created.

- [ ] Clarify Project Requirements
	<!-- Ask for project type, language, and frameworks if not specified. Skip if already provided. -->

- [ ] Scaffold the Project
	<!--
	Ensure that the previous step has been marked as completed.
	Call project setup tool with projectType parameter.
	Run scaffolding command to create project files and folders.
	Use '.' as the working directory.
	If no appropriate projectType is available, search documentation using available tools.
	Otherwise, create the project structure manually using available file creation tools.
	-->

- [ ] Customize the Project
	<!--
	Verify that all previous steps have been completed successfully and you have marked the step as completed.
	Develop a plan to modify codebase according to user requirements.
	Apply modifications using appropriate tools and user-provided references.
	Skip this step for "Hello World" projects.
	-->

- [ ] Install Required Extensions
	<!-- ONLY install extensions mentioned in the project setup information. Skip this step otherwise and mark as completed. -->

- [ ] Compile the Project
	<!--
	Verify that all previous steps have been completed.
	Install any missing dependencies.
	Run diagnostics and resolve any issues.
	Check for markdown files in project folder for relevant instructions on how to do this.
	-->

- [ ] Create and Run Task
	<!--
	Verify that all previous steps have been completed.
	Check https://code.visualstudio.com/docs/debugtest/tasks to determine if the project needs a task. If so, use the create_and_run_task to create and launch a task based on package.json, README.md, and project structure.
	Skip this step otherwise.
	 -->

- [ ] Launch the Project
	<!--
	Verify that all previous steps have been completed.
	Prompt user for debug mode, launch only if confirmed.
	 -->

- [ ] Ensure Documentation is Complete
	<!--
	Verify that all previous steps have been completed.
	Verify that README.md and the copilot-instructions.md file in the .github directory exists and contains current project information.
	Clean up the copilot-instructions.md file in the .github directory by removing all HTML comments.
	 -->

# Workspace instructions

- [x] Project requirements clarified: Java 17, Maven, Spring Boot 3, PostgreSQL and MySQL.
- [x] Four services scaffolded: products (8080), clients (8081), suppliers (8082), logs (8083).
- [x] CRUD layers and Bean Validation implemented for all services.
- [x] Docker Compose, local datasource configuration, and Spanish README added.
- [x] No additional VS Code extensions required.
- [x] README documents build and run steps. Maven validation could not run because Java, Maven, and Docker are not installed or available on PATH in this environment.
- [x] No launch task created; services were not started.

## Project conventions

- Keep each microservice independently runnable from its own Maven module.
- Use constructor injection, DTO records with Bean Validation, and `ResponseStatusException` for missing resources.
- Keep ports and database connection settings configurable with environment variables; defaults are for local development only.
- Products use `ProductRepository` and support case-insensitive name search.
- Logs use a generated `Long` identifier, `message`, and a server-managed timestamp.
- Update the Spanish README whenever endpoints, fields, or run instructions change.
