class WordleChecker {
  constructor() {
    this.wordList = [];
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
    this.originalWordList = [];
  }

  async initCheck() {
    const response = await fetch('sgb-words.txt');
    const text = await response.text();
    this.wordList = text.split('\n');
    this.originalWordList = [...this.wordList]; // Store the original word list
    this.attachEventListeners();
  }

  attachEventListeners() {
    document.getElementById('submitWord').addEventListener('click', () => {
      this.word = document.getElementById('inputWord').value.toUpperCase();
      this.result = document.getElementById('inputResult').value.toUpperCase();
      this.wordList = [...this.originalWordList]; // Reset wordList to the original state
      this.letterSorter(this.word, this.result);
      this.wordRemover();
    });
  }

  printStatus() {
    const possibleLetters = document.getElementById('possibleLetters');
    possibleLetters.textContent = this.possibleLetters.join(' ');

    const badLetters = document.getElementById('badLetters');
    badLetters.textContent = this.badLetters.join(' ');

    const finalWord = document.getElementById('finalWord');
    finalWord.textContent = this.finWord.join('');
  }

  printPossibleWords() {
    const possibleWords = document.getElementById('possibleWords');
    possibleWords.textContent = this.wordList.join(', ');
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
        if (!this.dict.has(input.charAt(i).toUpperCase())) {
          if (this.possibleLetters.includes(input.charAt(i))) {
            console.log("Letter " + input.charAt(i) + " skipped");
          } else {
            this.possibleLetters.push(input.charAt(i));
            console.log("Added possible letter: " + input.charAt(i));
          }
        }
      } else {
        const letter = input.charAt(i);
        const letterIndex = this.possibleLetters.indexOf(letter);
        if (letterIndex !== -1) {
          this.possibleLetters.splice(letterIndex, 1);
        }
        this.finWord[i] = letter;
        this.dict.set(letter, i);
        console.log("Added letter " + letter + " to the final word at index " + i);
      }
    }
    console.log("\nFinished looping through the results...");
    console.log("Successfully added all good or bad letters");
    this.printStatus();
  }

  wordRemover() {
    // Filtering out words with bad letters
    for (let i = 0; i < this.badLetters.length; i++) {
      const badLetter = this.badLetters[i].toLowerCase();
      this.wordList = this.wordList.filter(word => !word.includes(badLetter));
    }
  
    // Filtering out words with incorrect letter positions
    for (const [key, value] of this.dict.entries()) {
      this.wordList = this.wordList.filter(word => word.charAt(value).toUpperCase() === key);
    }
  
    // Filtering out words that don't have the yellow letters in the correct positions
    for (let i = 0; i < this.possibleLetters.length; i++) {
      const possibleLetter = this.possibleLetters[i].toLowerCase();
      this.wordList = this.wordList.filter(word => {
        const index = word.indexOf(possibleLetter);
        return index !== -1 && index !== this.dict.get(this.possibleLetters[i]);
      });
    }
  
    this.printPossibleWords();
  
    console.log("Final word list length:", this.wordList.length);
  }
  
  
}

// Main function
const wordle = new WordleChecker();
wordle.initCheck();
