// Lab5.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <stdio.h>
#include <malloc.h>
#include <string.h>

// The structure is created before main so that the fields and sruct can be accessed
// by functions outside of main.
struct Question {
	char question[100];
	int qType;
	struct Question* yes;
	struct Question* no;
};

// Functions that will be used later in the program
void add(Question* current);
void clean(Question* question);

int main()
{
	// The instantiation of the first question in the tree
	struct Question question;
	strcpy_s(question.question, "Is it a dog? ");
	question.qType = 1;
	question.yes = NULL;
	question.no = NULL;

	// Creating a pointer specifically for the root of the tree
	Question* head;
	head = &question;

	// Creating a pointer that will allow for iteration through the tree
	Question* current;

	// A yes-or-no variable to keep the while loop going and an answer variable to store the user's answer
	char yon[50] = "yes";
	char ans[50];

	// The outer while loop allows for the user to be able keep playing the game or quit
	while (!strcmp(yon, "yes")) {
		// Variable "tru" is used to keep the while loop iterting through the tree
		int tru = 1;

		// Setting the iterating pointer to the root
		current = head;

		// While loop iterating through the tree
		while (tru == 1) {

			// Printing the prompt then getting the input
			printf("%s\n", current->question);
			gets_s(ans, 49);

			// if-statement meant to determine if the user is on the final question or not
			if (current->qType == 1) {
				// Determine win or lose if the user is on the final question
				if (!strcmp(ans, "yes")) {
					printf("I win! :p\n");
					tru = 0;
				}
				else {
					// if lose, go to function to add new questions
					printf("I lose. :C\n");
					add(current);
					tru = 0;
				}
			}
			// If the user is not on the final question
			else {
				if (!strcmp(ans, "yes")) {
					current = current->yes;
				}
				else {
					current = current->no;
				}
			}
		}

		// Prompts the user if they want to keep playing, if not it cleans the program
		printf("Would you like to play again (yes/no)?\n");
		gets_s(yon, 49);
		if (!strcmp(yon, "no")) {
			clean(head);
		}
	}
}

	
void add(Question* current) {
	// Changing the type of the question so the program knows that later it isn't the final question
	current->qType = 0;

	// Saving the neccessary information to re-add the question being moved
	char tempQ[100];
	strcpy_s(tempQ, current->question);

	// Gets the question and animal from the user to add to the tree and starts setting the question into place
	char userQ[100];
	printf("What is a yes/no question to distinguish between the last animal and the animal you were thinking of?\n");
	gets_s(userQ, 100);
	strcpy_s(current->question, userQ);

	// Finds out whether the previous animal would be the yes or no node
	char yon[50];
	printf("Would the answer be yes or no for the last animal?\n");
	gets_s(yon, 49);

	// Preps and then performs concatination to create the "Is it a [animal]?"
	char animalQ[45];
	char animal[15];
	char last[40];
	printf("What is the animal you were thinking of?\n");
	gets_s(animal, 15);
	strcpy_s(animalQ, "Is it a ");
	strcpy_s(last, "? ");
	strcat_s(animalQ, animal);
	strcat_s(animalQ, last);

	// Creates the questions to be set as yes or no
	struct Question lastQ;
	lastQ.qType = 1;
	lastQ.no = NULL;
	lastQ.yes = NULL;
	struct Question newQ;
	strcpy_s(newQ.question, animalQ);
	newQ.qType = 1;
	newQ.no = NULL;
	newQ.yes = NULL;

	// If statement places the node in their corresponding locations based on the previous question
	if (!strcmp(yon, "yes")) {
		current->yes = &lastQ;
		strcpy_s(lastQ.question, tempQ);
		current->no = &newQ;
	}
	else {
		current->no = &lastQ;
		strcpy_s(lastQ.question, tempQ);
		current->yes = &newQ;
	}
}

void clean(Question* question) {
	// If statement checks to make sure the program doesn't attempt to delete an empty space
	if (question == NULL) return;
	// Method calls to work down the tree
	clean(question->yes);
	clean(question->no);
	// Stating the questions being deleted and then frees their space
	printf("Deleting %s\n", question->question);
	free(question);
}