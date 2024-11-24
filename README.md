# todo.Ping()
Сервис для управления задачами и анализа нагрузки студентов.

## Проблема
Плохой тайм-менеджмент снижает эффективность обучения и работы. Достаточно часто человеку достаточно тяжело реально оценивать объем задач. Отсутствие системы удобного хранения событий и задач с распределением по пространствам, а также метрики по ним.

## Функционал приложения 
Пользователю предлагается взможность организовывать свою учебу/работу по пространствам, в которые собираются задачи и события. Для этих пространств предполагается возможность приглашать других пользователей для организации рабочей и учебной деятельности. Сбор метрик и тегирование задач позволяет  пользователю лучше управлять своим временем и удобно распределять его.  

## User story
1. Преподаватель создаёт пространство и добавляет модераторов (модератор может изменять задачи и эвенты и добавлять учеников, но не может добавлять модераторов)
2. Преподаватели и модераторы добавляют учеников
3. Создаются задачи и эвенты. У каждой задачи есть свои теги. По тегам можно выдавать рекомендации по тайм-менеджменту относительно своего выполнения аналогичных задач и выполнения товарищей
4. У Ученика автоматически вносятся обновления в календарь по эвентам. (У ученика автоматически обновляются эвенты)
5. Ученик может добавлять выполнение задач в календарь относительно рекомендации по времени
6. Пользователю приходит уведомление перед и во время начала событий в календаре, для задач предлагается установить статус
