Square Calculator Опис: Програма обчислює квадрати чисел із масиву в багатопотоковому режимі за допомогою Java. Масив складається із 50 випадкових дробових чисел у діапазоні [0,5; 99,5]. Програма розбиває масив на частини (по 10 елементів) і обробляє кожну частину в окремому потоці, використовуючи ExecutorService та Callable.

Присутні в програмі функції: Генерація випадкових чисел - масив із 50 випадкових дробових чисел створюється у заданому діапазоні [0.5, 99.5]. Багатопотокова обробка - масив розділяється на частини, і кожна частина обробляється окремим потоком. Використання Future - результати обчислень збираються асинхронно через об'єкти Future. Перевірка стану завдань - перевірка через isDone() та isCancelled(). Збереження унікальних значень - результати зберігаються у потокобезпечному колекції CopyOnWriteArraySet. Вимірювання часу виконання - програма підраховує і виводить час роботи.

Як працює: Масив із 50 випадкових чисел у діапазоні [0.5, 99.5] генерується за допомогою Random.doubles().

Масив розділяється на підмасиви по 10 елементів для паралельної обробки.

Для кожної частини масиву створюється завдання (Callable), яке обчислює квадрати чисел. Завдання виконуються у потоках через ExecutorService.

Результати обчислень (квадрати чисел) збираються в потокобезпечну множину CopyOnWriteArraySet, яка забезпечує унікальність значень.

Програма виводить у консоль усі квадрати чисел та час виконання.

Приклад запуску: Generated array of numbers: [5.234, 7.894, 2.312, ...] pool-1-thread-1 processed: [27.398, 62.299, ...] pool-1-thread-2 processed: [47.123, 98.432, ...] Squares of numbers: [27.398, 62.299, 47.123, 98.432, ...] Program execution time: 52 ms

Як запустити: Треба скомпілювати файл SquareCalculator.java та запустити програму