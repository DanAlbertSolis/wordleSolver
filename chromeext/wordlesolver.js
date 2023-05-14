const fs = require('fs');

class WordleChecker {
  constructor(wordList) {
    this.wordList = wordList;
    this.badWordList = [];
    this.running = true;

    this.dict = new Map();
    this.finWord = new Array(5);
    this.word = '';
    this.result = '';
    this.possibleLetters = [];
    this.badLetters = [];
    this.finalWord = [];
    this.allGreen = false;
  }

  initCheck() {
    const stdin = require('readline-sync');
    console.log("What word was entered? (Common starter word is Crane)");
    this.word = stdin.question();

    console.log("Successfully initialized word: " + this.word);

    console.log("Enter the results of the initial guess in order from first letter to last letter (B = black (not used), G = green (correct spot), Y = yellow (used, but in the wrong spot), for example: BGYYB");
    this.result = stdin.question();
    this.letterSorter(this.word, this.result);

    this.wordRemover();
    console.log("wordRemover() finished");

    while (this.running) {
      this.checker();
    }
  }

  checker() {
    const stdin = require('readline-sync');
    console.log("What is the next word entered?");
    this.word = stdin.question();
    console.log("Successfully initialized word: " + this.word);
    console.log("Enter the results of the initial guess in order from first letter to last letter (B = black (not used), G = green (correct spot), Y = yellow (used, but in the wrong spot), for example: BGYYB");
    this.result = stdin.question();
    this.letterSorter(this.word, this.result);
    this.wordRemover();
    console.log("Is the word finished? Y/N");
    const yn = stdin.question();
    if (yn === "Y" || yn === "y") {
      this.running = false;
    }
  }

  printStatus() {
    console.log("The possible letters so far are: ");
    for (let i = 0; i < this.possibleLetters.length; i++) {
      process.stdout.write(this.possibleLetters[i] + " ");
    }
    console.log("");

    console.log("The bad letters so far are: ");
    for (let i = 0; i < this.badLetters.length; i++) {
      process.stdout.write(this.badLetters[i] + " ");
    }
    console.log("");

    console.log("The word we have so far is: ");
    for (let i = 0; i < this.finWord.length; i++) {
      process.stdout.write(String(this.finWord[i]));
    }
    console.log();
  }

  printPossibleWords() {
    for (let i = 0; i < this.wordList.length; i++) {
      if (i % 10 === 0) {
        process.stdout.write("\n");
      }
      process.stdout.write(this.wordList[i] + " ,");
    }
    console.log("There are " + this.wordList.length + " words");
  }

  letterSorter(input, result) {
    for (let i = 0; i < result.length; i++) {
      if (result.charAt(i) === 'B' || result.charAt(i) === 'b') {
        if (this.badLetters.includes(input.charAt(i)) || this.possibleLetters.includes(input.charAt(i))) {
          console.log("Letter " + input.charAt(i) + " skipped");
        } else {
          this.badLetters.push(input.charAt(i));
          console.log("Added bad letter: " + input.charAt(i));
        }
      } else if (result.charAt(i) === 'Y' || result.charAt(i) === 'y') {
        if (this.possibleLetters.includes(input.charAt(i))) {
          console.log("Letter " + input.charAt(i) + " skipped");
        } else {
          this.possibleLetters.push(input.charAt(i));
          console.log("Added possible letter: " + input.charAt(i));
        }
      } else {
        this.finWord[i] = input.charAt(i);
        this.dict.set(input.charAt(i), i);
        console.log("Added letter " + input.charAt(i) + " to the final word at index " + i);
      }
    }
    console.log("\nFinished looping through the results...");
    console.log("Successfully added all good or bad letters");
    this.printStatus();
  }

  wordRemover() {
    for (let i = 0; i < this.badLetters.length; i++) {
      for (let j = this.wordList.length - 1; j >= 0; j--) {
        const s = this.wordList[j];
        for (let k = 0; k < s.length; k++) {
          if (s.charAt(k) === this.badLetters[i]) {
            console.log("Removed word '" + this.wordList[j] + "' from the list because the letter " + s.charAt(k) + " is at index " + k + " and is equal to badLetter " + this.badLetters[i]);
            this.wordList.splice(j, 1);
            break;
          }
        }
      }
    }

    for (const [key, value] of this.dict.entries()) {
      for (let a = this.wordList.length - 1; a >= 0; a--) {
        const s = this.wordList[a];
        if (s.charAt(value) !== key) {
          console.log("Map remover is entered 2, removed word " + this.wordList[a] + " because " + s.charAt(value) + " doesnt equal " + key);
          this.wordList.splice(a, 1);
        }
      }
    }

    this.printPossibleWords();
  }
}

// Main function
(async () => {
  const wordList = fs.readFileSync('sgb-words.txt', 'utf8').split('\n');
  const wordle1 = new WordleChecker(wordList);
  wordle1.initCheck();
})();
