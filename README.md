# Продажа билетов на мероприятия

Консольное приложение на Java для продажи билетов на мероприятие.

## Возможности
- Покупка билета (стандартный или VIP, VIP дороже в 2.5 раза)
- Возврат билета по номеру
- Просмотр всех билетов и поиск по имени покупателя
- Контроль вместимости: билеты сверх лимита не продаются (SoldOutException)
- Сохранение продаж в файл tickets.txt и загрузка при запуске

## Запуск
```
mkdir out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out ui.Main
```

## Архитектура
- `model` — Ticket (абстрактный), StandardTicket, VipTicket, Event, интерфейс Priced
- `exception` — SoldOutException
- `util` — обобщённый класс Repository<T>
- `service` — TicketService (бизнес-логика)
- `storage` — FileStorage (работа с файлом)
- `ui` — Main (консольное меню)
