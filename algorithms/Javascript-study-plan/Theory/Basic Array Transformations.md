  # Basic Array Transformations in JavaScript

JavaScript arrays aren’t just lists of data. They’re Swiss army knives for bending data into whatever shape you want. Transformations like `map`, `filter`, `reduce`, and friends show up everywhere in production code — from front-end UI rendering to data pipelines in Node.js.

This document walks through the concept from the ground up, dives into how it works behind the scenes, shows real-world use cases, and finishes with interview-level questions.

---

## 1. What Are Array Transformations?

Array transformations are operations that take an input array, apply some logic to each element, and produce a new array or a single value. The goal is not mutation but conversion.

Think of it like passing ingredients through a machine and getting cookies, not touching the original flour.

---

## 2. Core Transformation Methods

These are the heavy hitters.

### `map()` – Transform values

Creates a new array based on modifying each element.

```js
const nums = [1, 2, 3];
const doubled = nums.map(n => n * 2); // [2, 4, 6]
```

### `filter()` – Remove values

Keeps only elements that pass a test.

```js
const nums = [1, 2, 3, 4];
const evens = nums.filter(n => n % 2 === 0); // [2, 4]
```

### `reduce()` – Fold array to a single value

Accumulates data.

```js
const nums = [1, 2, 3];
const sum = nums.reduce((acc, n) => acc + n, 0); // 6
```

### `forEach()` – Perform operations without returning

Used for side effects.

```js
[1, 2, 3].forEach(n => console.log(n));
```

### `find()` and `findIndex()`

Look for the first match.

```js
const users = [{id:1}, {id:2}];
const user = users.find(u => u.id === 2);
```

### `some()` and `every()`

Check boolean conditions across the array.

```js
nums.some(n => n > 10);   // at least one
nums.every(n => n > 0);   // all
```

---

## 3. Under the Hood: How Array Transformations Work

JavaScript treats these methods as higher-order functions — functions that take other functions as input.

When you call something like `map`, the engine:

1. Iterates the array
2. Calls your callback on each element
3. Builds a new array with the returned values

Important internal details:

* Transformations *never* mutate the original array (unless your callback does).
* They skip empty slots in sparse arrays.
* They preserve the original array length (except `filter`).

Each method uses the same callback signature:

```
callback(element, index, array)
```

This gives you full access to context during iteration.

---

## 4. Basic Implementations from Scratch

Re-implementing them helps build understanding.

### Custom `map`

```js
Array.prototype.myMap = function (fn) {
  const result = [];
  for (let i = 0; i < this.length; i++) {
    if (this.hasOwnProperty(i)) {
      result.push(fn(this[i], i, this));
    }
  }
  return result;
};
```

### Custom `filter`

```js
Array.prototype.myFilter = function (fn) {
  const result = [];
  for (let i = 0; i < this.length; i++) {
    if (this.hasOwnProperty(i) && fn(this[i], i, this)) {
      result.push(this[i]);
    }
  }
  return result;
};
```

### Custom `reduce`

```js
Array.prototype.myReduce = function (fn, init) {
  let acc = init;
  for (let i = 0; i < this.length; i++) {
    if (this.hasOwnProperty(i)) {
      acc = fn(acc, this[i], i, this);
    }
  }
  return acc;
};
```

These mimic the real thing closely enough to illustrate the behavior.

---

## 5. Real Industry Use Cases

These methods are everywhere in modern JavaScript systems.

### React: Building UI from data arrays

```js
const users = data.map(user => <UserCard key={user.id} {...user} />);
```

### Node.js: Transforming API responses

```js
const cleaned = response.items.map(item => ({
  id: item.id,
  name: item.title,
}));
```

### Filtering logs in monitoring systems

```js
const errors = logs.filter(log => log.level === "error");
```

### Reducing metrics

```js
const totalRequests = logs.reduce((acc, log) => acc + log.count, 0);
```

### Pipelines combining multiple transformations

```js
const result = data
  .filter(item => item.active)
  .map(item => item.value)
  .reduce((a, b) => a + b, 0);
```

This pattern is so common it’s basically a design language.

---

## 6. Interview Questions Based on Array Transformations

Expect these frequently.

### 1. Implement `map` without using built-in `map`.

Simple but shows understanding.

### 2. Explain the difference between `map` and `forEach`.

One returns a new array; the other doesn’t.

### 3. Use `reduce` to flatten an array.

```js
const flat = arr.reduce((acc, val) => acc.concat(val), []);
```

### 4. Why is `filter` non-mutating? How does it behave with sparse arrays?

Tests your knowledge of purity and hidden engine rules.

### 5. Build your own `groupBy` using `reduce`.

```js
const groupBy = (arr, key) => arr.reduce((acc, item) => {
  const k = item[key];
  (acc[k] ||= []).push(item);
  return acc;
}, {});
```

### 6. When is `reduce` the wrong tool?

They want to see if you’re forcing it where `map`/`filter` is better.

---

## 7. How To Think About Array Transformations

They’re small, composable behaviors. Chain them intelligently, avoid mutating inputs, and think in terms of flow.

Mastering these builds clean data processing pipelines and makes your code more declarative and predictable.

---

End of file.
